package com.example.android.moovies.di.module;

import android.content.Context;

import com.example.android.moovies.di.scope.MooviesScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @MooviesScope
    public Context context(){
        return context;
    }
}
