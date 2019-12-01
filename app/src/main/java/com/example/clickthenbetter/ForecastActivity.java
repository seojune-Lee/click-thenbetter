package com.example.clickthenbetter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

public class ForecastActivity extends AppCompatActivity {
    TextView degree, humid, temp_min, temp_max,risk,comment,local;
    RequestQueue mQueue;
    OpenAPIClient openAPIClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        init();

        openAPIClient = new OpenAPIClient();
        openAPIClient.getCurrentForecast(mQueue, degree, temp_max,temp_min,humid,local);


    }

    public void init(){
        degree = (TextView)findViewById(R.id.degree);
        humid = (TextView)findViewById(R.id.hum);
        temp_max = (TextView)findViewById(R.id.temMax);
        temp_min = (TextView)findViewById(R.id.tempMin);
        mQueue = Volley.newRequestQueue(this);
        risk = findViewById(R.id.risk);
        comment = findViewById(R.id.comment);
        local = findViewById(R.id.local);
    }

    public void getColdInfo(View view){
        openAPIClient.getCurrentDisease(mQueue,risk,comment,1);
    }
    public void getAshInfo(View view){
        openAPIClient.getCurrentDisease(mQueue,risk,comment,4);
    }
    public void getEyeInfo(View view){
        openAPIClient.getCurrentDisease(mQueue,risk,comment,2);
    }
    public void getSkinInfo(View view){
        openAPIClient.getCurrentDisease(mQueue,risk,comment,5);
    }
    public void getFoodInfo(View view){
        openAPIClient.getCurrentDisease(mQueue,risk,comment,3);
    }
    public void logOut(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
