package com.example.android.moovies.ui.common.mtv_list;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.example.android.moovies.domain.models.mtv.MtvListItem;
import com.example.android.moovies.domain.models.tv.TvListResponse;
import com.example.android.moovies.domain.models.tv.TvListResult;
import com.example.android.moovies.domain.use_case.GetMovieCollectionList;
import com.example.android.moovies.domain.use_case.GetMovieList;
import com.example.android.moovies.domain.use_case.GetTvList;
import com.example.android.moovies.domain.use_case.SearchMovie;
import com.example.android.moovies.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

class ListPresenter extends BasePresenter<ListMvpView> {

    @Inject
    GetMovieList getMovieList;
    @Inject
    GetTvList getTvList;
    @Inject
    GetMovieCollectionList getMovieCollectionList;
    @Inject
    SearchMovie searchMovie;

    @Inject
    ListPresenter(GetMovieList getMovieList, GetTvList getTvList, GetMovieCollectionList getMovieCollectionList, SearchMovie searchMovie) {
        this.getMovieList = getMovieList;
        this.getTvList = getTvList;
        this.getMovieCollectionList = getMovieCollectionList;
        this.searchMovie = searchMovie;
    }

    @Override
    public void attachView(ListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        getMovieList.dispose();
        getTvList.dispose();
    }

    void getList(int page, int id, int collection_id, int tab) {

        List<Integer> list = Arrays.asList(page, tab, id);

        if (tab == 0 || tab == 1 || tab == 2 || tab == 3 || tab == 8 || tab == 11 || tab == 12) {
            getMovieList.execute(new MovieListObserver(), list);
        } else if (tab == 4 || tab == 5 || tab == 6 || tab == 7 || tab == 9) {
            getTvList.execute(new TvListObserver(), list);
        } else if (tab == 10) {
            getMovieCollectionList.execute(new MovieCollectionObserver(), collection_id);
        }
    }

    private class MovieListObserver extends DisposableObserver<MovieListResponse> {
        @Override
        public void onNext(MovieListResponse value) {
            List<MovieListResult> mMovieListResultList = new ArrayList<>();
            mMovieListResultList.addAll(value.getResults());

            List<MtvListItem> mtvListItems = getMtvListItemsFromMovies(mMovieListResultList);

            getMvpView().setTotalPages(value.getTotalPages());
            getMvpView().showList(mtvListItems);
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
            List<MtvListItem> mtvListItems = getMtvListItemsFromMovies(mMovieListResultList);

            getMvpView().showList(mtvListItems);
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

    private class TvListObserver extends DisposableObserver<TvListResponse> {
        @Override
        public void onNext(TvListResponse value) {
            List<TvListResult> mTvListResultList = new ArrayList<>();
            mTvListResultList.addAll(value.getResults());

            List<MtvListItem> mtvListItems = getMtvListItemsFromTv(mTvListResultList);

            getMvpView().setTotalPages(value.getTotalPages());
            getMvpView().showList(mtvListItems);
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

    @NonNull
    private List<MtvListItem> getMtvListItemsFromMovies(List<MovieListResult> mMovieListResultList) {
        List<MtvListItem> mtvListItems = new ArrayList<>();

        for (MovieListResult movieListResult : mMovieListResultList) {

            MtvListItem mtvListItem = new MtvListItem(movieListResult.getId(),
                    movieListResult.getTitle(),
                    movieListResult.getPosterPath(),
                    movieListResult.getReleaseDate(),
                    movieListResult.getOverview(),
                    movieListResult.getVoteAverage(),
                    0.0f);

            mtvListItems.add(mtvListItem);
        }
        return mtvListItems;
    }

    private List<MtvListItem> getMtvListItemsFromTv(List<TvListResult> mTvListResultList) {
        List<MtvListItem> mtvListItems = new ArrayList<>();

        for (TvListResult tvListResult : mTvListResultList) {

            MtvListItem mtvListItem = new MtvListItem(tvListResult.getId(),
                    tvListResult.getName(),
                    tvListResult.getPosterPath(),
                    tvListResult.getFirstAirDate(),
                    tvListResult.getOverview(),
                    tvListResult.getVoteAverage(),
                    0.0f);

            mtvListItems.add(mtvListItem);
        }
        return mtvListItems;
    }
}
