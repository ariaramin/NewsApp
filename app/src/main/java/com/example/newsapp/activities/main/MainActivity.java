package com.example.newsapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.model.APIResponse;
import com.example.newsapp.model.News;
import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.adapter.TopNewsAdapter;
import com.example.newsapp.api.APIClient;
import com.example.newsapp.api.APIEndpoints;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    TextView date;
    SliderView slider;
    RecyclerView newsRecyclerView;
    ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityPresenter presenter = new MainActivityPresenter(this, new MainActivityModel());
    }

    @Override
    public void setUpView() {
        date = findViewById(R.id.dateTextView);
        slider = findViewById(R.id.newsSlider);
        newsRecyclerView = findViewById(R.id.newsRecylerView);
        loadingDialog = ProgressDialog.show(this, "",
                "Loading...", true);
    }

    @Override
    public void setDate() {
        String currentDate = new SimpleDateFormat("EEEE, MMMM dd").format(new Date());
        date.setText(currentDate);
    }

    @Override
    public void showProgress() {
        loadingDialog.show();
    }

    @Override
    public void hideProgress() {
        loadingDialog.dismiss();
    }

    @Override
    public void setDataToSlider(List<News> newsList) {
        List<News> topHeadlines = newsList.subList(0, 5);
        TopNewsAdapter sliderAdapter = new TopNewsAdapter(getApplicationContext(), topHeadlines);
        slider.setSliderAdapter(sliderAdapter);
        slider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        slider.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        slider.setScrollTimeInSec(5);
        slider.startAutoCycle();
    }

    @Override
    public void setDataToRecyclerView(List<News> newsList) {
        List<News> headlines = newsList.subList(5, newsList.size() - 1);
        NewsAdapter newsAdapter = new NewsAdapter(getApplicationContext(), headlines);
        newsRecyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void setMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}