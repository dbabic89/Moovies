package com.example.android.moovies.ui.movie_detail;

import com.example.android.moovies.data.models.movie.MovieDetail;
import com.example.android.moovies.ui.base.BaseMvpView;

public interface MovieDetailMvpView  extends BaseMvpView {

    void showMovieDetail(MovieDetail movieDetail);

    void showError();

    void showWatchlist(boolean watchlist);

    void showRating(int rating);

    void showList(boolean list);

}