package com.lence.testresultant;

import android.util.Log;

import com.lence.testresultant.api.App;
import com.lence.testresultant.model.StocksModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {
    Mvp mMvp;

    public Presenter(Mvp mvp) {
        mMvp = mvp;
    }

    public void load() {
        Log.e("load", "load");
        App.getApi().getStocks().enqueue(new Callback<StocksModel>() {
            @Override
            public void onResponse(Call<StocksModel> call, Response<StocksModel> response) {
                Log.e("Sections", response.code() + " " + response.message());
                if (response.code() == 200) {
                    //Log.e("body", response.body().getStock().toString());
                    mMvp.refreshList(response.body());
                } else {
                    mMvp.showMessage(response.code() + " " + response.message());
                }

                //mMvp.refreshList(response.body());
            }

            @Override
            public void onFailure(Call<StocksModel> call, Throwable t) {
                Log.e("Exception", call + " " + t);
            }
        });

    }
}
