package com.example.android.moovies.domain.repository;

import com.example.android.moovies.data.DataSource;
import com.example.android.moovies.data.remote.DataSourceNetwork;
import com.example.android.moovies.domain.models.MtvRating;
import com.example.android.moovies.domain.models.SearchQuery;
import com.example.android.moovies.domain.models.account.AccountStatesRated;
import com.example.android.moovies.domain.models.account.AccountStatesRating;
import com.example.android.moovies.domain.models.account.PostMovieToWatchlist;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.Rating;
import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.tv.TvDetail;
import com.example.android.moovies.domain.models.tv.TvListResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Repository implements DataSource {

    @Inject
    DataSourceNetwork dataSourceNetwork;

    @Inject
    public Repository(DataSourceNetwork dataSourceNetwork) {
        this.dataSourceNetwork = dataSourceNetwork;
    }

    @Override
    public Observable<MovieListResponse> getNowPlayingMovies(int page) {
        return dataSourceNetwork.getNowPlayingMovies(page);
    }

    @Override
    public Observable<MovieListResponse> getUpcomingMovies(int page) {
        return dataSourceNetwork.getUpcomingMovies(page);
    }

    @Override
    public Observable<MovieListResponse> getPopularMovies(int page) {
        return dataSourceNetwork.getPopularMovies(page);
    }

    @Override
    public Observable<MovieListResponse> getTopRatedMovies(int page) {
        return dataSourceNetwork.getTopRatedMovies(page);
    }

    @Override
    public Observable<MovieListResponse> getSimilarMovies(int movieId, int page) {
        return dataSourceNetwork.getSimilarMovies(movieId, page);
    }

    @Override
    public Observable<MovieListResponse> searchMovie(SearchQuery searchQuery) {
        return dataSourceNetwork.searchMovie(searchQuery);
    }

    @Override
    public Observable<CollectionDetail> getCollection(int movieId) {
        return dataSourceNetwork.getCollection(movieId);
    }

    @Override
    public Observable<TvListResponse> getAiringTodayTvs(int page) {
        return dataSourceNetwork.getAiringTodayTvs(page);
    }

    @Override
    public Observable<TvListResponse> getOnAirTvs(int page) {
        return dataSourceNetwork.getOnAirTvs(page);
    }

    @Override
    public Observable<TvListResponse> getPopularTvs(int page) {
        return dataSourceNetwork.getPopularTvs(page);
    }

    @Override
    public Observable<TvListResponse> getTopRatedTvs(int page) {
        return dataSourceNetwork.getTopRatedTvs(page);
    }

    @Override
    public Observable<TvListResponse> getSimilarTvs(int tvId, int page) {
        return dataSourceNetwork.getSimilarTvs(tvId, page);
    }

    @Override
    public Observable<TvDetail> getTvDetail(int tvId) {
        return dataSourceNetwork.getTvDetail(tvId);
    }

    @Override
    public Observable<MtvRating> getAccountTvStates(int tvId) {
        return dataSourceNetwork.getAccountTvStates(tvId);
    }

    @Override
    public Observable<MovieDetail> getMovieDetails(int movieId) {
        return dataSourceNetwork.getMovieDetails(movieId);
    }

    @Override
    public Observable<AccountStatesRated> getAccountStatesRated(int movieId) {
        return dataSourceNetwork.getAccountStatesRated(movieId);
    }

    @Override
    public Observable<AccountStatesRating> getAccountStatesRating(int movieId) {
        return dataSourceNetwork.getAccountStatesRating(movieId);
    }

    @Override
    public Observable<PostResponse> addMovieToWatchlist(PostMovieToWatchlist movieToWatchlist) {
        return dataSourceNetwork.addMovieToWatchlist(movieToWatchlist);
    }

    @Override
    public Observable<PostResponse> addMovieRating(Rating rating) {
        return dataSourceNetwork.addMovieRating(rating);
    }

    @Override
    public Observable<PostResponse> deleteMovieRating(Rating rating) {
        return dataSourceNetwork.deleteMovieRating(rating);
    }

    @Override
    public Observable<MovieListResponse> getRatedMovies(int page) {
        return dataSourceNetwork.getRatedMovies(page);
    }

    @Override
    public Observable<MovieListResponse> getMovieWatchlist(int page) {
        return dataSourceNetwork.getMovieWatchlist(page);
    }

    @Override
    public Observable<Celebrity> getCelebrity(int id) {
        return dataSourceNetwork.getCelebrity(id);
    }
}
