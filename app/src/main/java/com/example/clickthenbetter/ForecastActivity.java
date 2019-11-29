package com.example.clickthenbetter;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ForecastActivity extends AppCompatActivity {
    TextView degree, humid, temp_min, temp_max,risk,comment,local;
    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        init();

        OpenAPIClient openAPIClient = new OpenAPIClient();
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
}
