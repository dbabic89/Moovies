package com.example.android.moovies;

import android.app.Activity;
import android.app.Application;

import com.example.android.moovies.di.component.ApplicationComponent;
import com.example.android.moovies.di.component.DaggerApplicationComponent;
import com.example.android.moovies.di.module.ApplicationModule;


public class Moovies extends Application {

    ApplicationComponent mApplicationComponent;

    public static Moovies get(Activity activity) {
        return (Moovies) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();


        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }

}
