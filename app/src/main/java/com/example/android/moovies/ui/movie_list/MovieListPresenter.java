package com.example.android.moovies.ui.movie_list;

import android.util.Log;

import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.example.android.moovies.domain.use_case.GetMovieCollectionList;
import com.example.android.moovies.domain.use_case.GetMovieList;
import com.example.android.moovies.domain.use_case.SearchMovie;
import com.example.android.moovies.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

class MovieListPresenter extends BasePresenter<MovieListMvpView> {

    @Inject GetMovieList getMovieList;
    @Inject GetMovieCollectionList getMovieCollectionList;
    @Inject SearchMovie searchMovie;

    @Inject
    MovieListPresenter(GetMovieList getMovieList, GetMovieCollectionList getMovieCollectionList, SearchMovie searchMovie) {
        this.getMovieList = getMovieList;
        this.getMovieCollectionList = getMovieCollectionList;
        this.searchMovie = searchMovie;
    }

    @Override
    public void attachView(MovieListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        getMovieList.dispose();
    }


    void getMovies(int page, int movieId, int collection_id, int tab) {
        List<Integer> list = Arrays.asList(page, tab, movieId);

        if (tab == 5){
            getMovieCollectionList.execute(new MovieCollectionObserver(), collection_id);
        } else {
            getMovieList.execute(new MovieListObserver(), list);
        }
    }

    private class MovieListObserver extends DisposableObserver<MovieListResponse> {
        @Override
        public void onNext(MovieListResponse value) {
            List<MovieListResult> mMovieListResultList = new ArrayList<>();
            mMovieListResultList.addAll(value.getResults());
            getMvpView().showMovies(mMovieListResultList);
        }

        @Override
        public void onError(Throwable e) {
            getMvpView().showError();
            Log.i("TAG", e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

    private class MovieCollectionObserver extends DisposableObserver<CollectionDetail> {
        @Override
        public void onNext(CollectionDetail value) {
            List<MovieListResult> mMovieListResultList = new ArrayList<>();
            mMovieListResultList.addAll(value.getParts());
            getMvpView().showMovies(mMovieListResultList);
        }

        @Override
        public void onError(Throwable e) {
            getMvpView().showError();
            Log.i("TAG", e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }
}
