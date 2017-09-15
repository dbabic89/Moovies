package com.example.android.moovies.domain.repository;

import com.example.android.moovies.data.remote.DataSourceNetwork;
import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.models.SearchQuery;
import com.example.android.moovies.domain.models.account.AccountStatesRated;
import com.example.android.moovies.domain.models.account.AccountStatesRating;
import com.example.android.moovies.domain.models.account.PostMovieToWatchlist;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.Rating;
import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieRepositoryImpl implements MovieRepository{

    @Inject DataSourceNetwork dataSourceNetwork;

    @Inject
    public MovieRepositoryImpl(DataSourceNetwork dataSourceNetwork) {
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
    public Observable<MovieListResponse> getSimilar(int movieId) {
        return dataSourceNetwork.getSimilar(movieId);
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
