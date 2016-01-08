package com.example.gferreira.myapplication.backend;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String jokeData;
    private String errorMesage;

    public String getJokeData() {
        return jokeData;
    }

    public String getErrorMesage() {
        return errorMesage;
    }

    public void setErrorMesage(String errorMesage) {
        this.errorMesage = errorMesage;
    }

    public void setJokeData(String jokeData) {
        this.jokeData = jokeData;
    }
}