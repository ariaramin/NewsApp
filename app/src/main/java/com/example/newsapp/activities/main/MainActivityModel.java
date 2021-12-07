package com.example.newsapp.activities.main;

import com.example.newsapp.api.APIClient;
import com.example.newsapp.api.APIEndpoints;
import com.example.newsapp.model.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityModel implements MainActivityContract.Model {

    @Override
    public void getNewsList(OnFinishedListener onFinishedListener, String country) {
        String API_KEY = "24b5419d8e0a4b3eb615b662024adbc3";
        APIEndpoints request = APIClient.getAPIClient().create(APIEndpoints.class);
        request.getTopHeadlines(country, API_KEY).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onFinishedListener.onFinished(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
