package com.example.myappportfolio.builditbigger;

import android.os.AsyncTask;

import com.example.gferreira.myapplication.backend.myApi.MyApi;
import com.example.gferreira.myapplication.backend.myApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by gferreira on 1/7/2016.
 */
public class JokeEndpointsAsyncTask extends AsyncTask<Void, Void, MyBean> {
    private static MyApi jokeApiService = null;
    private BaseView baseView;

    //set context to show message from andoid library jokes via gce
    public JokeEndpointsAsyncTask(BaseView baseView){
        this.baseView = baseView;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Optional Tasks - Add Loading Indicator
        baseView.showProgressBar();
    }

    @Override
    protected MyBean doInBackground(Void...params) {

        //get joke
        MyBean jokeBean  = new MyBean();

        if(jokeApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - 10.0.3.2 is localhost's IP address in Genymotion
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            jokeApiService = builder.build();
        }

        try {

            jokeBean = jokeApiService.getJoke().execute();
            return jokeBean;

        } catch (IOException e) {
            jokeBean.setErrorMesage(e.getMessage());
            return jokeBean;
        }
    }

    @Override
    protected void onPostExecute(MyBean jokeBean) {

        //get joke done, hide progress bar
        baseView.hideProgressBar();

        if(null==jokeBean.getJokeData()) {
            baseView.showErrorMessage(jokeBean);
        } else {
            baseView.showJoke(jokeBean);
        }

    }
}
