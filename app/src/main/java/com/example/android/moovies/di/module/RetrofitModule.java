package com.example.android.moovies.di.module;

import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.di.scope.MooviesAppScope;
import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.models.search.SearchResults;
import com.example.android.moovies.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class RetrofitModule {

        @Provides
        @MooviesAppScope
        TmdbInterface tmdbService(Retrofit retrofit) {
            return retrofit.create(TmdbInterface.class);
        }

        @Provides
        @MooviesAppScope
        public Gson gson() {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(AccountStates.class, new AccountStates.AccountStatesDeserializer());
            gsonBuilder.registerTypeAdapter(SearchResults.class, new SearchResults.SearchResultsDeserializer());
            return gsonBuilder.create();
        }

        @Provides
        @MooviesAppScope
        Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
            return new Retrofit.Builder()
                    .baseUrl(Constants.TMDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

}
