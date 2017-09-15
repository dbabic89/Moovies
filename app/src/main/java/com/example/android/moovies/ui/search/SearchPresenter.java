package com.example.android.moovies.ui.search;

import android.util.Log;

import com.example.android.moovies.domain.models.SearchQuery;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.example.android.moovies.domain.use_case.SearchMovie;
import com.example.android.moovies.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

class SearchPresenter extends BasePresenter<SearchMvpView> {

    @Inject
    SearchMovie searchMovie;

    @Inject
    SearchPresenter(SearchMovie searchMovie) {
        this.searchMovie = searchMovie;
    }

    @Override
    public void attachView(SearchMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        searchMovie.dispose();
    }


    void getMovies(String query, int page) {
        searchMovie.execute(new SearchMovieObserver(), new SearchQuery(query, page));
    }

    void getMoreMovies(String query, int page) {
        searchMovie.execute(new MoreMovieObserver(), new SearchQuery(query, page));
    }

    private class SearchMovieObserver extends DisposableObserver<MovieListResponse> {
        @Override
        public void onNext(MovieListResponse value) {
            List<MovieListResult> mMovieListResultList = new ArrayList<>();

            if (value.getResults() != null) {
                mMovieListResultList.addAll(value.getResults());
                getMvpView().showSearchResults(mMovieListResultList);
            } else
                getMvpView().showMoviesEmpty();
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

    private class MoreMovieObserver extends DisposableObserver<MovieListResponse> {
        @Override
        public void onNext(MovieListResponse value) {

            List<MovieListResult> mMovieListResultList = new ArrayList<>();

            if (value.getResults() != null) {
                mMovieListResultList.addAll(value.getResults());
                getMvpView().showMoreMovies(mMovieListResultList);
            } else
                getMvpView().showMoviesEmpty();
        }

        @Override
        public void onError(Throwable e) {
            getMvpView().showError();
        }

        @Override
        public void onComplete() {

        }
    }
}
