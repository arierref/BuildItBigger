package com.example.myappportfolio.builditbigger;

import com.example.gferreira.myapplication.backend.myApi.model.MyBean;

/**
 * Created by gferreira on 1/7/2016.
 */
public interface BaseView {

    void executeJokeAsyncTask();
    void showJoke(MyBean jokeBean);
    void showErrorMessage(MyBean jokeBean);
    void showProgressBar();
    void hideProgressBar();
}
