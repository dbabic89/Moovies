package com.example.android.moovies.di.module;

import com.example.android.moovies.di.scope.HomeActivityScope;
import com.example.android.moovies.ui.home.HomeActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    private final HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @HomeActivityScope
    public HomeActivity homeActivity() {
        return homeActivity;
    }
}
