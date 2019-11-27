package com.karbyshev.randomuser;

import android.app.Application;

import com.karbyshev.randomuser.data.service.RetrofitService;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RetrofitService.init();
    }
}
