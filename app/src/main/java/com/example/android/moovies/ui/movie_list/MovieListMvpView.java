package com.example.android.moovies.ui.movie_list;

import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface MovieListMvpView extends BaseMvpView {

    void showMovies(List<MovieListResult> movies);

    void showProgress();

    void removeProgress();

    void showMoviesEmpty();

    void showError();

    void openMovieDetails(int id);

}
