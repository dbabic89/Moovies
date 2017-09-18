package com.example.android.moovies.di.module;

import android.content.Context;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.di.scope.MooviesAppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Moovies application;

    public ApplicationModule(Moovies application) {
        this.application = application;
    }

    @Provides
    @MooviesAppScope
    Context provideApplicationContext() {
        return this.application;
    }
}