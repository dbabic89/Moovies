package com.example.android.moovies.ui.movie_detail;

import com.example.android.moovies.data.models.movie.Backdrop;
import com.example.android.moovies.data.models.movie.Cast;
import com.example.android.moovies.data.models.movie.CollectionDetail;
import com.example.android.moovies.data.models.movie.Country;
import com.example.android.moovies.data.models.movie.Genre;
import com.example.android.moovies.data.models.movie.Keyword;
import com.example.android.moovies.data.models.movie.MovieDetail;
import com.example.android.moovies.data.models.movie.Reviews;
import com.example.android.moovies.data.models.movie.Videos;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface MovieDetailMvpView  extends BaseMvpView {

    void showMovieDetail(MovieDetail movieDetail);

    void showCertification(List<Country> countries);

    void showGenres(List<Genre> genreList);

    void showWatchlist(boolean watchlist);

    void showRating(int rating);

    void showList(boolean list);

    void showImagesForSlider(List<Backdrop> backdropList);

    void showVideosForSlider(Videos videos, String title, String overview);

    void showCollection(CollectionDetail collectionDetail);

    void showReviews(Reviews reviews);

    void showCast(List<Cast> castList);

    void showSimilarMovies(int movieId);

    void showKeywords(List<Keyword> keywordsList);

    void showError();

}