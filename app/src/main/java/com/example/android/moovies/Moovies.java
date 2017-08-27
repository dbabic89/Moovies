package com.example.android.moovies;

import android.app.Application;

import com.example.android.moovies.di.component.ApplicationComponent;

public class Moovies extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
//
//        mApplicationComponent = DaggerApplicationComponent.builder()
//                .contextModule(new ContextModule(this))
//                .build();

    }
}
