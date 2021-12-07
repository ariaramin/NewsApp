package com.example.newsapp.api;

import com.example.newsapp.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIEndpoints {

    @GET("top-headlines")
    Call<APIResponse> getTopHeadlines(@Query("country") String country,
                                      @Query("apiKey") String API_KEY);
}
