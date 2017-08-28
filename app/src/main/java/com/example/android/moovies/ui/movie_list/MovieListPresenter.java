package com.example.android.moovies.ui.movie_list;

import android.util.Log;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.models.movie.CollectionDetail;
import com.example.android.moovies.data.models.movie.MovieListResponse;
import com.example.android.moovies.data.models.movie.MovieListResult;
import com.example.android.moovies.data.remote.TmdbClient;
import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


class MovieListPresenter extends BasePresenter<MovieListMvpView> {

    private TmdbInterface mTmdbInterface;
    private Observable<MovieListResponse> movieListObservable;
    private Observable<CollectionDetail> collectionListObservable;
    private List<MovieListResult> mMovieListResultList;
    private int x = -1;

    MovieListPresenter(int x) {

        mTmdbInterface = TmdbClient.getTmdbV3Client().create(TmdbInterface.class);
        mMovieListResultList = new ArrayList<>();
        this.x = x;
    }

    @Override
    public void attachView(MovieListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    void getMovies(int page, int movieId, int collection_id) {
        Log.i("TAG", "MovieListPresenter getMovies " + movieId);

        switch (x) {
            case 0:
                movieListObservable = mTmdbInterface.getNowPlayingMovies2(BuildConfig.TMDB_APIKEY, "en-US", page);
                startMovieListObservable();
                break;
            case 1:
                movieListObservable = mTmdbInterface.getUpcomingMovies2(BuildConfig.TMDB_APIKEY, "en-US", page);
                startMovieListObservable();
                break;
            case 2:
                movieListObservable = mTmdbInterface.getPopularMovies2(BuildConfig.TMDB_APIKEY, "en-US", page);
                startMovieListObservable();
                break;
            case 3:
                movieListObservable = mTmdbInterface.getTopRatedMovies2(BuildConfig.TMDB_APIKEY, "en-US", page);
                startMovieListObservable();
                break;
            case 4:
                movieListObservable = mTmdbInterface.getSimilar(movieId, BuildConfig.TMDB_APIKEY);
                startMovieListObservable();
                break;
            case 5:
                collectionListObservable = mTmdbInterface.getCollection(collection_id, BuildConfig.TMDB_APIKEY);

                collectionListObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<CollectionDetail>() {
                            @Override
                            public void onNext(CollectionDetail value) {
                                if (value != null) {
                                    mMovieListResultList.addAll(value.getParts());
                                    getMvpView().showMovies(mMovieListResultList);
                                } else {
                                    getMvpView().showMoviesEmpty();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                getMvpView().showError();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }

    private void startMovieListObservable() {
        movieListObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MovieListResponse>() {
                    @Override
                    public void onNext(MovieListResponse value) {
                        if (value != null) {
                            mMovieListResultList.addAll(value.getResults());
                            getMvpView().showMovies(mMovieListResultList);
                        } else {
                            getMvpView().showMoviesEmpty();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
