package com.example.android.moovies.di.module;

import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.di.scope.MooviesScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class TmdbInterfaceModule {

    @Provides
    @MooviesScope
    public TmdbInterface tmdbInterface(Retrofit retrofit){
        return retrofit.create(TmdbInterface.class);
    }
    @Provides
    @MooviesScope
    public Gson gson(){
        return new GsonBuilder().create();
    }

    @Provides
    @MooviesScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }
}
