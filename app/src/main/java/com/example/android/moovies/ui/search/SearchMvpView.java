package com.example.android.moovies.ui.search;

import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface SearchMvpView extends BaseMvpView {

    void showSearchResults(List<MovieListResult> movies);

    void showMoreMovies(List<MovieListResult> movies);

    void showMoviesEmpty();

    void showError();

    void openMovieDetails(int id);

}
