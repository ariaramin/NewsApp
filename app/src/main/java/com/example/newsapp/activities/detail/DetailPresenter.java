package com.example.newsapp.activities.detail;
import com.example.newsapp.model.News;

public class DetailPresenter implements DetailContract.Presenter {
    private final DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
        view.setUpView();
        getNews();
    }

    @Override
    public void getNews() {
        News data = view.getViewIntent().getParcelableExtra("news");
        view.setData(data);
    }
}
