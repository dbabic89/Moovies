package com.example.android.moovies.di.component;

import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.di.module.PicassoModule;
import com.example.android.moovies.di.module.TmdbInterfaceModule;
import com.example.android.moovies.di.scope.MooviesScope;
import com.squareup.picasso.Picasso;

import dagger.Component;

@MooviesScope
@Component(modules = {PicassoModule.class, TmdbInterfaceModule.class})
public interface ApplicationComponent {

    Picasso getPicasso();

    TmdbInterface getTmdbInterface();


}
