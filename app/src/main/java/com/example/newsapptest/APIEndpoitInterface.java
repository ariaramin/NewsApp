package com.example.newsapptest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIEndpointInterface {

    @GET("top-headlines")
    Call<APIResponse> getTopHeadlines(@Query("country") String country,
                                      @Query("apiKey") String API_KEY);
}
