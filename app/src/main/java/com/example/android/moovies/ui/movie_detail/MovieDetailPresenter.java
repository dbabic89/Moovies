package com.example.android.moovies.ui.movie_detail;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.models.movie.MovieDetail;
import com.example.android.moovies.data.remote.TmdbClient;
import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.ui.base.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailPresenter extends BasePresenter<MovieDetailMvpView> {

    private TmdbInterface mTmdbInterface;

    MovieDetailPresenter() {

        mTmdbInterface = TmdbClient.getTmdbV3Client().create(TmdbInterface.class);
    }

    @Override
    public void attachView(MovieDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    void getMovieDetails(int movieId) {

        Observable<MovieDetail> observable = mTmdbInterface.getMovieDetails(movieId, BuildConfig.TMDB_APIKEY, "images,videos,credits,similar,reviews,keywords,releases");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MovieDetail>() {
                    @Override
                    public void onNext(MovieDetail value) {

                        if (value != null) {
                            getMvpView().showMovieDetail(value);
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

    void getAccountStates(){

    }
}
