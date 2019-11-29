package com.example.clickthenbetter;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenAPIClient extends AppCompatActivity {

    String houseTem = "24℃  45%";
    String houseDust = "42.08 ㎍/㎥";
    String[] riskGrade = {"관심","주의","경고","위험"};
    String[] diseases = {"감기", "천식", "식중독", "피부염","눈병"};
    DiseaseInfo diseaseInfo;
    //TextView mTextViewResult;
    //RequestQueue mQueue;
    //WeatherInfo weatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diseaseInfo = new DiseaseInfo();



    }

    public void getCurrentWeather(final TextView mTextViewResult, final TextView house_tem, RequestQueue mQueue, Double lat, Double lon) {
        String url = "https://openweathermap.org/data/2.5/weather?q=Seoul&appid=b6907d289e10d714a6e88b30761fae22";
        url = url.concat("&lon="+lon.toString()+"&lat="+lat.toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            Double temp = main.getDouble("temp");
                            Double tem_max = main.getDouble("temp_max");
                            Double tem_min = main.getDouble("temp_min");
                            String hum = main.getString("humidity");
                            mTextViewResult.append(temp+"℃  "+ hum+"%"+"\n"+50.03+"㎍/㎥");
                            house_tem.append(houseTem+"\n"+houseDust);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    public void getCurrentDust(final TextView mTextViewResult) {
        String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnStatsSvc/getMsrstnAcctoLastDcsnDnsty?stationName=권선구&searchCondition=DAILY" +
                "&pageNo=1&numOfRows=10&ServiceKey=d%2BVtlnK922p4WsvWXCl8vRqyKYqR5T%2Fpl2%2F5CtG5sxhxBFpx%2Fwkx64InRbvmAmmrYpa%2B6gNoRZfaY3uVnWdzag%3D%3D";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject item = response.getJSONObject("item");
                            Double dust = item.getDouble("pm10Avg");
                            mTextViewResult.append(50.03+"㎍/㎥"+ "\n\n");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        //mTextViewResult.append(50.03+"㎍/㎥"+ "\n\n");
    }

    public void getCurrentForecast(RequestQueue mQueue, final TextView currentTem, final TextView temMax,final TextView temMin,
                                  final TextView hum,final TextView local){
        String url = "https://openweathermap.org/data/2.5/weather?q=Seoul&appid=b6907d289e10d714a6e88b30761fae22";
        String url_dis = "http://apis.data.go.kr/B550928/dissForecastInfoSvc/getDissForecastInfo?serviceKey=d%2BVtlnK922p4WsvWXCl8vRqyKYqR5T%2Fpl2%2F5CtG5sxhxBFpx%2Fwkx64InRbvmAmmrYpa%2B6gNoRZfaY3uVnWdzag%3D%3D" +
                "&numOfRows=10&pageNo=1&type=json&dissCd=1&znCd=11";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            Double temp = main.getDouble("temp");
                            Double tem_max = main.getDouble("temp_max");
                            Double tem_min = main.getDouble("temp_min");
                            String humid = main.getString("humidity");
                            currentTem.setText(temp.toString());
                            temMax.setText(tem_max.toString()+"˚");
                            temMin.setText(tem_min.toString()+"˚");
                            hum.setText(humid+"%");
                            local.setText("수원시");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        //TextView local = findViewById(R.id.local);
        mQueue.add(request);
    }

    public void getCurrentDisease(){
        String url = "http://apis.data.go.kr/B550928/dissForecastInfoSvc/getDissForecastInfo?serviceKey=d%2BVtlnK922p4WsvWXCl8vRqyKYqR5T%2Fpl2%2F5CtG5sxhxBFpx%2Fwkx64InRbvmAmmrYpa%2B6gNoRZfaY3uVnWdzag%3D%3D" +
                "&numOfRows=10&pageNo=1&type=json&dissCd=1&znCd=11";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            Double temp = main.getDouble("temp");
                            Double tem_max = main.getDouble("temp_max");
                            Double tem_min = main.getDouble("temp_min");
                            String humid = main.getString("humidity");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        //TextView local = findViewById(R.id.local);
        //mQueue.add(request);
    }






}
