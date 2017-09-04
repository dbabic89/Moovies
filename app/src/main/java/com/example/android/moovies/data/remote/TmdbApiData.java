package com.example.android.moovies.data.remote;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.ui.common.IconAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TmdbApiData {

    private TmdbInterface tmdbInterface;
    private IconAdapter mIconAdapter;
    private Context context;

    public TmdbApiData(Context context) {
        this.context = context;
        tmdbInterface = TmdbClient.getTmdbClient().create(TmdbInterface.class);
    }

    public void getNowPlayingMovies(RecyclerView recyclerView){

        Call<MovieListResponse> call = tmdbInterface.getNowPlayingMovies(BuildConfig.TMDB_APIKEY, "en-US", 1);
        callBack(call, recyclerView, context);
    }

    public void getUpcomingMovies(RecyclerView recyclerView){

        Call<MovieListResponse> call = tmdbInterface.getUpcomingMovies(BuildConfig.TMDB_APIKEY, "en-US", 1);
        callBack(call, recyclerView, context);
    }

    public void getPopularMovies(RecyclerView recyclerView){

        Call<MovieListResponse> call = tmdbInterface.getPopularMovies(BuildConfig.TMDB_APIKEY, "en-US", 1);
        callBack(call, recyclerView, context);
    }

    public void getTopRatedMovies(RecyclerView recyclerView){

        Call<MovieListResponse> call = tmdbInterface.getTopRatedMovies(BuildConfig.TMDB_APIKEY, "en-US", 1);
        callBack(call, recyclerView, context);
    }

    private void callBack (Call<MovieListResponse> call, final RecyclerView recyclerView, final Context context){

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.code() == 200){
                    mIconAdapter = new IconAdapter(context);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                    recyclerView.setAdapter(mIconAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });

    }
}
