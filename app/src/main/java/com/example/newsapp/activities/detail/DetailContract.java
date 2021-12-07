package com.example.newsapp.activities.detail;

import android.content.Intent;

import com.example.newsapp.model.News;

public interface DetailContract {
    interface View {
        void setUpView();

        Intent getViewIntent();

        void setData(News news);
    }

    interface Presenter {
        void getNews();
    }
}
