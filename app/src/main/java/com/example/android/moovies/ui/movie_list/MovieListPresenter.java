package com.example.android.moovies.ui.movie_list;

import com.example.android.moovies.BuildConfig;
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


class MovieListPresenter extends BasePresenter<MovieListMvpView>{

    private TmdbInterface mTmdbInterface;
    private Observable<MovieListResponse> mObservable;
    private List<MovieListResult> mMovieListResultList;
    private int x = 0;

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

    List<MovieListResult> getMovies(){

        switch (x) {
            case 1:
                mObservable = mTmdbInterface.getNowPlayingMovies2(BuildConfig.TMDB_APIKEY, "en-US", 1);
                break;
            case 2:
                mObservable = mTmdbInterface.getUpcomingMovies2(BuildConfig.TMDB_APIKEY, "en-US", 1);
                break;
            case 3:
                mObservable = mTmdbInterface.getPopularMovies2(BuildConfig.TMDB_APIKEY, "en-US", 1);
                break;
            case 4:
                mObservable = mTmdbInterface.getTopRatedMovies2(BuildConfig.TMDB_APIKEY, "en-US", 1);
                break;
        }

        mObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MovieListResponse>() {
                    @Override
                    public void onNext(MovieListResponse value) {

                        if (value != null){
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

        return mMovieListResultList;
    }
}
