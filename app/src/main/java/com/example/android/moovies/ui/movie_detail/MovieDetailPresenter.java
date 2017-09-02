package com.example.android.moovies.ui.movie_detail;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.data.models.account.AccountStatesRated;
import com.example.android.moovies.data.models.account.AccountStatesRating;
import com.example.android.moovies.data.models.account.PostMovieToWatchlist;
import com.example.android.moovies.data.models.account.PostResponse;
import com.example.android.moovies.data.models.account.Rated;
import com.example.android.moovies.data.models.movie.Country;
import com.example.android.moovies.data.models.movie.MovieDetail;
import com.example.android.moovies.data.remote.TmdbClient;
import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.ui.base.BasePresenter;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.StringFormating;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MovieDetailPresenter extends BasePresenter<MovieDetailMvpView> {

    private TmdbInterface mTmdbInterface;
    private SharedPreferencesManager mSharedPreferencesManager;
    private MovieDetailFragment movieDetailFragment;

    MovieDetailPresenter(MovieDetailFragment movieDetailFragment, SharedPreferencesManager mSharedPreferencesManager) {

        this.movieDetailFragment = movieDetailFragment;
        this.mSharedPreferencesManager = mSharedPreferencesManager;
        mTmdbInterface = TmdbClient.getTmdbClient().create(TmdbInterface.class);
    }

    @Override
    public void attachView(MovieDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    void getMovieDetails(final int movieId) {

        Observable<MovieDetail> observable = mTmdbInterface.getMovieDetails(movieId, BuildConfig.TMDB_APIKEY, "images,videos,credits,similar,reviews,keywords,releases");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MovieDetail>() {
                    @Override
                    public void onNext(MovieDetail value) {
                            setDetailsToView(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieDetailFragment.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void setDetailsToView(MovieDetail movie) {

        movieDetailFragment.showMovieDetail(movie.getTitle(), String.valueOf(movie.getVoteAverage()), String.valueOf(Math.round(movie.getVoteCount())), movie.getStatus());

        if (movie.getBackdropPath() != null) movieDetailFragment.showBackdrop(Constants.URL_IMG_BACKDROP + movie.getBackdropPath());
        else movieDetailFragment.showNoBackdrop();

        if (movie.getTagline() != null) movieDetailFragment.showTagline(movie.getTagline());
        else  movieDetailFragment.showTagline(" N/A");

        if (!movie.getCredits().getCrew().isEmpty()) movieDetailFragment.showDirectedBy(movie.getCredits().getCrew());
        else movieDetailFragment.showNoDirectedBy();

        if (movie.getPosterPath() != null) movieDetailFragment.showPoster(Constants.URL_IMG_MOVIE_POSTER + movie.getPosterPath());
        else movieDetailFragment.showNoPoster();

        if (movie.getOverview() != null) movieDetailFragment.showOverview(movie.getOverview());
        else movieDetailFragment.showOverview("No overview");

        for (Country country : movie.getReleases().getCountries()){
            if (country.getIso31661().equals("US")) {

                if (!country.getCertification().isEmpty()) movieDetailFragment.showCertification(country.getCertification());
                else movieDetailFragment.showNoCertification();

                if (!country.getReleaseDate().isEmpty()) movieDetailFragment.showReleaseDate(country.getReleaseDate());
                else movieDetailFragment.showNoReleaseDate();
            }
        }

        if (movie.getRuntime() != 0) movieDetailFragment.showDuration(movie.getRuntime());
        else movieDetailFragment.showNoDuration();

        movieDetailFragment.showGenres(movie.getGenres());

        if (!movie.getImages().getBackdrops().isEmpty()) movieDetailFragment.showImages(movie.getImages().getBackdrops());
        else movieDetailFragment.showNoImages();

        if (!movie.getVideos().getResults().isEmpty()) movieDetailFragment.showVideos(movie.getVideos(), movie.getTitle(), movie.getOverview());
        else movieDetailFragment.showNoVideos();

        if (movie.getBelongsToCollection() != null) movieDetailFragment.showCollection(movie.getBelongsToCollection());
        if (!movie.getReviews().getResults().isEmpty()) movieDetailFragment.showReviews(movie.getReviews());

        movieDetailFragment.showCast(movie.getCredits().getCast());

        if (!movie.getProductionCompanies().isEmpty()) movieDetailFragment.showProductionCompanies(StringFormating.companyFormating(movie.getProductionCompanies()));
        else movieDetailFragment.showProductionCompanies("N/A");

        if (!movie.getProductionCountries().isEmpty()) movieDetailFragment.showProductionCountries(StringFormating.countriesFormating(movie.getProductionCountries()));
        else movieDetailFragment.showProductionCountries("N/A");

        if (!movie.getSpokenLanguages().isEmpty()) movieDetailFragment.showSpokenLanguage(StringFormating.getSpokenLanguage(movie.getSpokenLanguages()));
        else movieDetailFragment.showSpokenLanguage("N/A");

        if (movie.getBudget() != 0) movieDetailFragment.showBudget(StringFormating.currencyFormating(movie.getBudget()));
        else movieDetailFragment.showBudget("N/A");

        if (movie.getRevenue() != 0) movieDetailFragment.showRevenue(StringFormating.currencyFormating(movie.getRevenue()));
        else movieDetailFragment.showRevenue("N/A");

        if (!movie.getSimilar().getResults().isEmpty()) movieDetailFragment.showSimilarMovies(movie.getId());
        if (!movie.getKeywords().getKeywordsList().isEmpty()) movieDetailFragment.showKeywords(movie.getKeywords().getKeywordsList());
    }

    void getAccountStatesRated(final int movieId) {

        Observable<AccountStatesRated> observable1 = mTmdbInterface.getAccountStatesRated(movieId, BuildConfig.TMDB_APIKEY, mSharedPreferencesManager.getSessionId());
        observable1
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<AccountStatesRated>() {
                    @Override
                    public void onNext(AccountStatesRated value) {
                        movieDetailFragment.showWatchlist(value.isWatchlist());
                        if (!value.isRated()) {
                            movieDetailFragment.showRating(0);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getAccountStatesRating(movieId);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void getAccountStatesRating(final int movieId) {

        Observable<AccountStatesRating> observable2 = mTmdbInterface.getAccountStatesRating(movieId, BuildConfig.TMDB_APIKEY, mSharedPreferencesManager.getSessionId());
        observable2
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<AccountStatesRating>() {
                    @Override
                    public void onNext(AccountStatesRating value) {
                        movieDetailFragment.showWatchlist(value.isWatchlist());
                        movieDetailFragment.showRating(value.getRated().getValue());
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieDetailFragment.showWatchlist(false);
                        movieDetailFragment.showRating(0);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void addMovieToWatchlist(final int movieId, boolean watchlist){

        Call<PostResponse> call = mTmdbInterface.addMovieToWatchlist(mSharedPreferencesManager.getAccountId(), BuildConfig.TMDB_APIKEY, mSharedPreferencesManager.getSessionId(), new PostMovieToWatchlist("movie", movieId, watchlist));
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {

            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });

    }

    void addMovieRating(int movieId, final int rating){

        Call<PostResponse> call = mTmdbInterface.addMovieRating(movieId, BuildConfig.TMDB_APIKEY, mSharedPreferencesManager.getSessionId(), new Rated(rating));
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                movieDetailFragment.showRating(rating);
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });

    }

    void deleteMovieRating(int movieId, final int rating) {
        Call<PostResponse> call = mTmdbInterface.deleteMovieRating(movieId, BuildConfig.TMDB_APIKEY, mSharedPreferencesManager.getSessionId());
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                movieDetailFragment.showRating(rating);
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });
    }
}
