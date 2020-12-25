package com.example.mvvmdaggerapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    public static final String BASE_URL = "https://api.github.com/";
    public static Retrofit retrofit;
    public static RetrofitService service;

    public static RetrofitService getInStance() {
        if(service == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            service = retrofit.create(RetrofitService.class);
        }
        return service;
    }
}
