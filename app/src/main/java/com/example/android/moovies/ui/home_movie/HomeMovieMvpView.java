package com.example.android.moovies.ui.home_movie;

import com.example.android.moovies.ui.base.BaseMvpView;

public interface HomeMovieMvpView extends BaseMvpView {

    void showNowPlayingMovies();

    void showUpcomingMovies();

    void showPopularMovies();

    void showTopRatedMovies();

}
