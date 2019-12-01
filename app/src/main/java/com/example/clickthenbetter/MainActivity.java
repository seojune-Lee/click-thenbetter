package com.example.clickthenbetter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewResult;
    TextView dustTxt, house_tem;
    RequestQueue mQueue;

    private Double lon, lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //weatherInfo = new WeatherInfo();
        dustTxt = (TextView)findViewById(R.id.dust);
        house_tem = (TextView)findViewById(R.id.house_tem);
        mTextViewResult = findViewById(R.id.tem_hum);
        mQueue = Volley.newRequestQueue(this);


        final LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT>=23&& ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
            }
        else{
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            setLon(location.getLongitude());
            setLat(location.getLatitude());
        }



        OpenAPIClient openAPIClient = new OpenAPIClient();
        openAPIClient.getCurrentWeather(mTextViewResult,house_tem,mQueue,lon,lat);


    }

    public void startForcast(View view){
        Intent intent = new Intent(this, ForecastActivity.class);
        startActivity(intent);
    }

    public void startRecommend(View view){
        Intent intent = new Intent(this, RecommendActivity.class);
        startActivity(intent);
    }

    public void logOut(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }




}
