package com.example.mvvmdaggerapi;

import com.example.mvvmdaggerapi.model.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("users/blackmiaool/repos")
    Call<List<Repos>> getAllItem();
}
