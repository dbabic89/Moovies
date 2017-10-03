package com.example.android.moovies.ui.movie_detail;

import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.Keyword;
import com.example.android.moovies.domain.models.movie.Reviews;
import com.example.android.moovies.domain.models.mtv.Cast;
import com.example.android.moovies.domain.models.mtv.Crew;
import com.example.android.moovies.domain.models.mtv.Genre;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.domain.models.mtv.Videos;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface MovieDetailMvpView extends BaseMvpView {

    void showMovieDetail(String title, String voteRating, String voteCount, String status);

    void showBackdrop(String imageUrl);

    void showNoBackdrop();

    void showGenres(List<Genre> genreList);

    void showTagline(String tagline);

    void showPoster(String imageUrl);

    void showNoPoster();

    void showCertification(String certification);

    void showNoCertification();

    void showReleaseDate(String releaseDate);

    void showNoReleaseDate();

    void showDuration(int duration);

    void showNoDuration();

    void showDirectedBy(List<Crew> crewList);

    void showOverview(String overview);

    void showWatchlist(boolean watchlist);

    void showRating(int rating);

    void showList(boolean list);

    void showImages(Images images);

    void showNoImages();

    void showVideos(Videos videos, String title, String overview);

    void showNoVideos();

    void showCollection(CollectionDetail collectionDetail);

    void showReviews(Reviews reviews);

    void showCast(List<Cast> castList);

    void showOriginalTitle(String originalTitle);

    void showProductionCompanies(String productionCompanies);

    void showProductionCountries(String productionCountries);

    void showSpokenLanguage(String spokenLanguages);

    void showBudget(String budget);

    void showRevenue(String revenue);

    void showSimilarMovies(int movieId);

    void showKeywords(List<Keyword> keywordsList);

    void showError();

}