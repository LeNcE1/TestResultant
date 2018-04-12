package com.lence.testresultant.api;

import android.app.Application;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {
    private static Api sApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://phisix-api3.appspot.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sApi = retrofit.create(Api.class);
    }

    public static Api getApi() {
        return sApi;
    }
}
