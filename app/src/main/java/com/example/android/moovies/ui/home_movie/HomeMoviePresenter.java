package com.example.android.moovies.ui.home_movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.android.moovies.data.remote.TmdbApiData;
import com.example.android.moovies.ui.base.BasePresenter;

public class HomeMoviePresenter extends BasePresenter<HomeMovieMvpView> {

    TmdbApiData tmdbApiData;

    public HomeMoviePresenter(Context context) {
        tmdbApiData = new TmdbApiData(context);
    }

    @Override
    public void attachView(HomeMovieMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getNowPlayingMovies(RecyclerView recyclerView) {
        tmdbApiData.getNowPlayingMovies(recyclerView);
    }

    public void getUpcomingMovies(RecyclerView recyclerView) {
        tmdbApiData.getUpcomingMovies(recyclerView);
    }

    public void getPopularMovies(RecyclerView recyclerView) {
        tmdbApiData.getPopularMovies(recyclerView);
    }

    public void getTopratedMovies(RecyclerView recyclerView) {
        tmdbApiData.getTopRatedMovies(recyclerView);
    }
}
