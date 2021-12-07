package com.example.newsapp.activities.main;

import com.example.newsapp.model.News;

import java.util.List;

public interface MainActivityContract {
    interface Model {

        interface OnFinishedListener {
            void onFinished(List<News> newsList);

            void onFailure(Throwable t);
        }

        void getNewsList(OnFinishedListener onFinishedListener, String country);
    }

    interface View {
        void setUpView();

        void setDate();

        void showProgress();

        void hideProgress();

        void setDataToSlider(List<News> newsList);

        void setDataToRecyclerView(List<News> newsList);

        void setMessage(String message);
    }

    interface Presenter {

        void requestDataFromServer(String country);

    }
}
