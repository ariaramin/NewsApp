package com.example.newsapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Initialize variable
    TextView date;
    SliderView slider;
    RecyclerView newsRecyclerView;
    ProgressDialog loadingDialog;

    private final String API_KEY = "24b5419d8e0a4b3eb615b662024adbc3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.dateTextView);
        slider = findViewById(R.id.newsSlider);
        newsRecyclerView = findViewById(R.id.newsRecylerView);

        date.setText(getDate());

       loadingDialog = ProgressDialog.show(this, "",
                "Loading...", true);

        APIEndpointInterface request = APIClient.getAPIClient().create(APIEndpointInterface.class);
        request.getTopHeadlines("us", API_KEY).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<News> topHeadlines = response.body().getArticles().subList(0,5);
                    TopNewsAdapter sliderAdapter = new TopNewsAdapter(getApplicationContext(), topHeadlines);
                    slider.setSliderAdapter(sliderAdapter);
                    slider.setIndicatorAnimation(IndicatorAnimationType.WORM);
                    slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    slider.setScrollTimeInSec(3);
                    slider.startAutoCycle();

                    List<News> headlines = response.body().getArticles().subList(5, response.body().getArticles().size() - 1);
                    NewsAdapter newsAdapter = new NewsAdapter(getApplicationContext(), headlines);
                    newsRecyclerView.setAdapter(newsAdapter);

                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.w("onFailure", t);
            }
        });
    }

    private String getDate() {
        String currentDate = new SimpleDateFormat("EEEE, MMMM dd").format(new Date());
        return currentDate;
    }
}