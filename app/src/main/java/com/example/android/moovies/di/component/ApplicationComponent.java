package com.example.android.moovies.di.component;

import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.di.module.ApplicationModule;
import com.example.android.moovies.di.module.PicassoModule;
import com.example.android.moovies.di.module.RetrofitModule;
import com.example.android.moovies.di.scope.MooviesAppScope;
import com.squareup.picasso.Picasso;

import dagger.Component;

@MooviesAppScope
@Component(modules = {ApplicationModule.class, RetrofitModule.class, PicassoModule.class})
public interface ApplicationComponent {

    Picasso getPicasso();

    TmdbInterface getTmdbInterface();

}
