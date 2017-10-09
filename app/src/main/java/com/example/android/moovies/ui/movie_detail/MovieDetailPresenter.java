package com.example.android.moovies.ui.movie_detail;

import android.util.Log;

import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.models.account.MtvRating;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.PostToWatchlist;
import com.example.android.moovies.domain.models.account.Rated;
import com.example.android.moovies.domain.models.movie.Country;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.observers.AddToWatchlistObserver;
import com.example.android.moovies.domain.use_case.AddMovieRating;
import com.example.android.moovies.domain.use_case.AddToWatchlist;
import com.example.android.moovies.domain.use_case.DeleteMovieRating;
import com.example.android.moovies.domain.use_case.GetAccountStatesMovie;
import com.example.android.moovies.domain.use_case.GetMovieDetails;
import com.example.android.moovies.ui.base.BasePresenter;
import com.example.android.moovies.utils.StringFormating;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

import static com.example.android.moovies.utils.Constants.URL_BACKDROP;
import static com.example.android.moovies.utils.Constants.URL_POSTER;

class MovieDetailPresenter extends BasePresenter<MovieDetailMvpView> {

    @Inject
    GetMovieDetails getMovieDetails;

    @Inject
    GetAccountStatesMovie getAccountStatesMovie;

    @Inject
    AddToWatchlist addToWatchlist;

    @Inject
    AddMovieRating addMovieRating;

    @Inject
    DeleteMovieRating deleteMovieRating;

    private int rating = 0;

    @Inject
    MovieDetailPresenter() {
    }

    @Override
    public void attachView(MovieDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        getMovieDetails.dispose();
        getAccountStatesMovie.dispose();
        addToWatchlist.dispose();
        addMovieRating.dispose();
        deleteMovieRating.dispose();
    }

    void getMovieDetails(final int movieId) {
        getMovieDetails.execute(new MovieDetailObserver(), movieId);
    }

    void getAccountStatesRating(int movieId) {
        getAccountStatesMovie.execute(new AccountStatesRatingObserver(), movieId);
    }

    void addToWatchlist(final int movieId, boolean watchlist) {
        addToWatchlist.execute(new AddToWatchlistObserver(), new PostToWatchlist("movie", movieId, watchlist));
    }

    void addRating(int movieId, int rating) {
        addMovieRating.execute(new RatingObserver(), new MtvRating(movieId, new Rated(rating)));
        this.rating = rating;
    }

    void deleteRating(int movieId, int rating) {
        deleteMovieRating.execute(new RatingObserver(), new MtvRating(movieId, new Rated(rating)));
        this.rating = rating;
    }

    private void setDetailsToView(MovieDetail movie) {

        getMvpView().showMovieDetail(movie.getTitle(), String.valueOf(movie.getVoteAverage()), String.valueOf(Math.round(movie.getVoteCount())), movie.getStatus());

        if (!movie.getTitle().equals(movie.getOriginalTitle()))
            getMvpView().showOriginalTitle(movie.getOriginalTitle());

        if (movie.getBackdropPath() != null)
            getMvpView().showBackdrop(URL_BACKDROP + movie.getBackdropPath());
        else getMvpView().showNoBackdrop();

        if (movie.getTagline() != null) getMvpView().showTagline(movie.getTagline());
        else getMvpView().showTagline(" N/A");

        if (!movie.getCredits().getCrew().isEmpty())
            getMvpView().showDirectedBy(movie.getCredits().getCrew());

        if (movie.getPosterPath() != null)
            getMvpView().showPoster(URL_POSTER + movie.getPosterPath());
        else getMvpView().showNoPoster();

        if (movie.getOverview() != null) getMvpView().showOverview(movie.getOverview());
        else getMvpView().showOverview("No overview");

        for (Country country : movie.getReleases().getCountries()) {
            if (country.getIso31661().equals("US")) {

                if (!country.getCertification().isEmpty())
                    getMvpView().showCertification(country.getCertification());
                else getMvpView().showNoCertification();

                if (!country.getReleaseDate().isEmpty())
                    getMvpView().showReleaseDate(country.getReleaseDate());
                else getMvpView().showNoReleaseDate();
            }
        }

        if (movie.getRuntime() != 0) getMvpView().showDuration(movie.getRuntime());
        else getMvpView().showNoDuration();

        getMvpView().showGenres(movie.getGenres());

        if (!movie.getImages().getBackdrops().isEmpty())
            getMvpView().showImages(movie.getImages());
        else getMvpView().showNoImages();

        if (!movie.getVideos().getResults().isEmpty())
            getMvpView().showVideos(movie.getVideos(), movie.getTitle(), movie.getOverview());
        else getMvpView().showNoVideos();

        if (movie.getBelongsToCollection() != null)
            getMvpView().showCollection(movie.getBelongsToCollection());
        if (!movie.getReviews().getResults().isEmpty())
            getMvpView().showReviews(movie.getReviews());

        if (!movie.getCredits().getCast().isEmpty())
            getMvpView().showCast(movie.getCredits().getCast());

        if (!movie.getProductionCompanies().isEmpty())
            getMvpView().showProductionCompanies(StringFormating.companyFormating(movie.getProductionCompanies()));
        else getMvpView().showProductionCompanies("N/A");

        if (!movie.getProductionCountries().isEmpty())
            getMvpView().showProductionCountries(StringFormating.countriesFormating(movie.getProductionCountries()));
        else getMvpView().showProductionCountries("N/A");

        if (!movie.getSpokenLanguages().isEmpty())
            getMvpView().showSpokenLanguage(StringFormating.getSpokenLanguage(movie.getSpokenLanguages()));
        else getMvpView().showSpokenLanguage("N/A");

        if (movie.getBudget() != 0)
            getMvpView().showBudget(StringFormating.currencyFormating(movie.getBudget()));
        else getMvpView().showBudget("N/A");

        if (movie.getRevenue() != 0)
            getMvpView().showRevenue(StringFormating.currencyFormating(movie.getRevenue()));
        else getMvpView().showRevenue("N/A");

        if (!movie.getSimilar().getResults().isEmpty())
            getMvpView().showSimilarMovies(movie.getId());
        if (!movie.getKeywords().getKeywordsList().isEmpty())
            getMvpView().showKeywords(movie.getKeywords().getKeywordsList());
    }

    private class MovieDetailObserver extends DisposableObserver<MovieDetail> {
        @Override
        public void onNext(MovieDetail value) {
            setDetailsToView(value);
        }

        @Override
        public void onError(Throwable e) {
            Log.i("TAG", e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

    private class AccountStatesRatingObserver extends DisposableObserver<AccountStates> {
        @Override
        public void onNext(AccountStates value) {
            getMvpView().showWatchlist(value.isWatchlist());
            if (value.getRating() != 0)getMvpView().showRating(value.getRating());
            else getMvpView().showRating(0);
        }

        @Override
        public void onError(Throwable e) {
            getMvpView().showWatchlist(false);
            getMvpView().showRating(0);
        }

        @Override
        public void onComplete() {

        }
    }

    private class RatingObserver extends DisposableObserver<PostResponse> {
        @Override
        public void onNext(PostResponse value) {
            getMvpView().showRating(rating);
        }

        @Override
        public void onError(Throwable e) {
            Log.i("TAG", e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }
}
