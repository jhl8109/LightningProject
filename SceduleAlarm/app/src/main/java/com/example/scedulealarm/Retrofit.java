package com.example.scedulealarm;

import com.example.scedulealarm.item.Content;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public  interface Retrofit {
    @FormUrlEncoded
    @POST("save")
    Call<SendData> postData(@Field("schedule") String schedule,@Field("checked") Boolean checked);

    @GET("save")
    Call<List<SendData>> getData();
}
