package com.example.android.moovies.ui.movie_list;

import android.util.Log;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.movie.MovieListResult;
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
    private int x = -1;

    MovieListPresenter(int x) {
        mTmdbInterface = TmdbClient.getTmdbClient().create(TmdbInterface.class);
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


        switch (x) {
            case 0:
                movieListObservable = mTmdbInterface.getNowPlayingMovies2(BuildConfig.TMDB_APIKEY, "en-US", page, "US");
                startMovieListObservable();
                break;
            case 1:
                movieListObservable = mTmdbInterface.getUpcomingMovies2(BuildConfig.TMDB_APIKEY, "en-US", page, "US");
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

                Log.i("TAG", "collectionListObservable");

                collectionListObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<CollectionDetail>() {
                            @Override
                            public void onNext(CollectionDetail value) {
                                Log.i("TAG", "onNext");
                                if (value != null) {
                                    List<MovieListResult> mMovieListResultList= new ArrayList<>();
                                    Log.i("TAG", "mMovieListResultList");
                                    mMovieListResultList.addAll(value.getParts());
                                    Log.i("TAG", "mMovieListResultList.addAll(value.getParts());");
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
                            List<MovieListResult> mMovieListResultList = new ArrayList<>();
//                            getMvpView().showProgress();
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
//                        getMvpView().removeProgress();
                    }
                });
    }
}
