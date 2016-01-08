package com.example.myappportfolio.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gferreira.myapplication.backend.myApi.model.MyBean;
import com.example.myappportfolio.androidlibrary.JokeViewActivity;

public class BaseActivity extends ActionBarActivity implements BaseView {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProgressBar();
    }

    private void initProgressBar(){

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showJoke(MyBean jokeBean) {
        Intent i = new Intent(this, JokeViewActivity.class);
        i.putExtra(JokeViewActivity.INTENT_EXTRA_PARAM_FOR_JOKE, jokeBean.getJokeData());
        this.startActivity(i);
    }

    @Override
    public void showErrorMessage(MyBean jokeBean) {
        Toast.makeText(this, jokeBean.getErrorMesage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void executeJokeAsyncTask() {
        new JokeEndpointsAsyncTask(this).execute();
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
