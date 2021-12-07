package com.example.newsapp.activities.main;

import com.example.newsapp.model.News;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter, MainActivityContract.Model.OnFinishedListener {

    private final MainActivityContract.Model model;
    private final MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view, MainActivityContract.Model model) {
        this.model = model;
        this.view = view;
        view.setUpView();
        view.setDate();
        requestDataFromServer("us");
    }

    @Override
    public void requestDataFromServer(String country) {
        if (view != null) {
            view.showProgress();
        }
        model.getNewsList(this, country);
    }

    @Override
    public void onFinished(List<News> newsList) {
        view.hideProgress();
        view.setDataToSlider(newsList);
        view.setDataToRecyclerView(newsList);
    }

    @Override
    public void onFailure(Throwable t) {
        view.hideProgress();
        view.setMessage("Something went wrong!");
    }
}
