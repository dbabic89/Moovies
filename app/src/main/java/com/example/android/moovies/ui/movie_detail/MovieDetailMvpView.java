package com.example.android.moovies.ui.movie_detail;

import com.example.android.moovies.data.models.movie.Backdrop;
import com.example.android.moovies.data.models.movie.Cast;
import com.example.android.moovies.data.models.movie.CollectionDetail;
import com.example.android.moovies.data.models.movie.Crew;
import com.example.android.moovies.data.models.movie.Genre;
import com.example.android.moovies.data.models.movie.Keyword;
import com.example.android.moovies.data.models.movie.Reviews;
import com.example.android.moovies.data.models.movie.Videos;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface MovieDetailMvpView  extends BaseMvpView {

    void showMovieDetail(String movieTitle, float movieRating);

    void showBackdrop(String imageUrl);

    void showNoBackdrop();

    void showGenres(List<Genre> genreList);

    void showTagline(String tagline);

    void showPoster(String imageUrl);

    void showNoPoster();

    void showCertification(String certification);

    void showNoCertification();

    void showReleaseDate (String releaseDate);

    void showNoReleaseDate();

    void showDuration(int duration);

    void showNoDuration();

    void showDirectedBy(List<Crew> crewList);

    void showNoDirectedBy();

    void showOverview(String overview);

    void showNoOverview();

    void showWatchlist(boolean watchlist);

    void showRating(int rating);

    void showList(boolean list);

    void showImages(List<Backdrop> backdropList);

    void showNoImages();

    void showVideos(Videos videos, String title, String overview);

    void showNoVideos();

    void showCollection(CollectionDetail collectionDetail);

    void showReviews(Reviews reviews);

    void showCast(List<Cast> castList);

    void showProductionCompanies(String productionCompanies);

    void showProductionCountries(String productionCountries);

    void showSpokenLanguage(String spokenLanguages);

    void showBudget(String budget);

    void showRevenue(String revenue);

    void showSimilarMovies(int movieId);

    void showKeywords(List<Keyword> keywordsList);

    void showError();

}