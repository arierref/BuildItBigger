package com.example.myappportfolio.builditbigger;

import android.test.AndroidTestCase;

import com.example.gferreira.myapplication.backend.myApi.model.MyBean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by gferreira on 1/7/2016.
 */
public class JokeEndpointsAsyncTaskTest extends AndroidTestCase implements BaseView{

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void testGetJokeBean(){

        executeJokeAsyncTask();

        try {

            countDownLatch.await(30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            MyBean jokeBean = new MyBean();
            jokeBean.setErrorMesage(e.getMessage());
            showErrorMessage(jokeBean);
        }

    }

    @Override
    public void showJoke(MyBean jokeBean) {
        assertNotNull("Joke Text received...", jokeBean.getJokeData());
    }

    @Override
    public void showErrorMessage(MyBean jokeBean) {
        fail(jokeBean.getErrorMesage());
    }


    @Override
    public void executeJokeAsyncTask() {
        new JokeEndpointsAsyncTask(this).execute();
    }


    //test not use
    @Override
    public void showProgressBar() {}

    //test not use
    @Override
    public void hideProgressBar() {}
}