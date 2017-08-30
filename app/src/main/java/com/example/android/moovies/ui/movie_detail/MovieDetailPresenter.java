package com.example.android.moovies.ui.movie_detail;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.data.models.account.AccountStatesRated;
import com.example.android.moovies.data.models.account.AccountStatesRating;
import com.example.android.moovies.data.models.account.PostMovieToWatchlist;
import com.example.android.moovies.data.models.account.PostResponse;
import com.example.android.moovies.data.models.account.Rated;
import com.example.android.moovies.data.models.movie.MovieDetail;
import com.example.android.moovies.data.remote.TmdbClient;
import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.ui.base.BasePresenter;

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

                        if (value != null) {
                            movieDetailFragment.showMovieDetail(value);
                            movieDetailFragment.showCertification(value.getReleases().getCountries());
                            movieDetailFragment.showGenres(value.getGenres());
                            movieDetailFragment.showImagesForSlider(value.getImages().getBackdrops());
                            movieDetailFragment.showVideosForSlider(value.getVideos(), value.getTitle(), value.getOverview());
                            movieDetailFragment.showCollection(value.getBelongsToCollection());
                            movieDetailFragment.showReviews(value.getReviews());
                            movieDetailFragment.showCast(value.getCredits().getCast());
                            movieDetailFragment.showSimilarMovies(value.getId());
                            movieDetailFragment.showKeywords(value.getKeywords().getKeywordsList());
                        }
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
