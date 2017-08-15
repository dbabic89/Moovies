package com.example.android.moovies.ui.movie_list;

import com.example.android.moovies.data.models.movie.MovieListResult;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

public interface MovieListMvpView extends BaseMvpView {

        void showMovies(List<MovieListResult> movies);

        void showMovieProgress(boolean show);

        void showMoviesEmpty();

        void showError();

        void openMovieDetails(int id);
}
