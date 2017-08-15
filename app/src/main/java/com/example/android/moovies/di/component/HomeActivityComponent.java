package com.example.android.moovies.di.component;

import com.example.android.moovies.di.module.HomeActivityModule;
import com.example.android.moovies.di.scope.HomeActivityScope;
import com.example.android.moovies.ui.home.HomeActivity;

import dagger.Component;

@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = ApplicationComponent.class)
public interface HomeActivityComponent {

    void injectHomeActivity(HomeActivity homeActivity);
}
