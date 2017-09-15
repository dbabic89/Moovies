package com.example.android.moovies.data.remote;

import android.util.Log;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.DataSource;
import com.example.android.moovies.data.local.SharedPreferencesManager;
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

public class DataSourceNetwork implements DataSource{

    @Inject TmdbInterface tmdbInterface;
    @Inject SharedPreferencesManager sharedPreferencesManager;

    private String apiKey = BuildConfig.TMDB_APIKEY;
    private String sessionId;
    private String language = "en-US";
    private int accoutId;

    @Inject
    DataSourceNetwork(TmdbInterface tmdbInterface, SharedPreferencesManager sharedPreferencesManager) {
        this.tmdbInterface = tmdbInterface;
        this.sharedPreferencesManager = sharedPreferencesManager;
        accoutId = sharedPreferencesManager.getAccountId();
        sessionId = sharedPreferencesManager.getSessionId();
    }

    @Override
    public Observable<MovieListResponse> getNowPlayingMovies(int page) {
        return tmdbInterface.getNowPlayingMovies(apiKey, language, page, "US");
    }

    @Override
    public Observable<MovieListResponse> getUpcomingMovies(int page) {
        return tmdbInterface.getUpcomingMovies(apiKey, language, page, "US");
    }

    @Override
    public Observable<MovieListResponse> getPopularMovies(int page) {
        return tmdbInterface.getPopularMovies(apiKey, language, page);
    }

    @Override
    public Observable<MovieListResponse> getTopRatedMovies(int page) {
        return tmdbInterface.getTopRatedMovies(apiKey, language, page);
    }

    @Override
    public Observable<MovieListResponse> getSimilar(int movieId) {
        return tmdbInterface.getSimilar(movieId, apiKey);
    }

    @Override
    public Observable<MovieListResponse> searchMovie(SearchQuery searchQuery) {
        Log.i("TAG", "DataSourceNetwork searchMovie " + searchQuery.getPage());
        return tmdbInterface.searchMovies(apiKey, searchQuery.getQuery(), searchQuery.getPage());
    }

    @Override
    public Observable<CollectionDetail> getCollection(int movieId) {
        return tmdbInterface.getCollection(movieId, apiKey);
    }

    @Override
    public Observable<MovieDetail> getMovieDetails(int movieId) {
        return tmdbInterface.getMovieDetails(movieId, apiKey, "images,videos,credits,similar,reviews,keywords,releases");
    }

    @Override
    public Observable<AccountStatesRated> getAccountStatesRated(int movieId) {
        return tmdbInterface.getAccountStatesRated(movieId, apiKey, sessionId);
    }

    @Override
    public Observable<AccountStatesRating> getAccountStatesRating(int movieId) {
        return tmdbInterface.getAccountStatesRating(movieId, apiKey, sessionId);
    }

    @Override
    public Observable<PostResponse> addMovieToWatchlist(PostMovieToWatchlist movieToWatchlist) {
        return tmdbInterface.addMovieToWatchlist(accoutId, apiKey, sessionId, movieToWatchlist);
    }

    @Override
    public Observable<PostResponse> addMovieRating(Rating rating) {
        return tmdbInterface.addMovieRating(rating.getMovieId(), apiKey, sessionId, rating.getRated());
    }

    @Override
    public Observable<PostResponse> deleteMovieRating(Rating rating) {
        return tmdbInterface.deleteMovieRating(rating.getMovieId(), apiKey, sessionId);
    }

    @Override
    public Observable<MovieListResponse> getRatedMovies(int page) {
        return tmdbInterface.getRatedMovies(accoutId, apiKey, sessionId, language, page);
    }

    @Override
    public Observable<MovieListResponse> getMovieWatchlist(int page) {
        return tmdbInterface.getWatchlist(accoutId, apiKey, sessionId, language, page);
    }

    @Override
    public Observable<Celebrity> getCelebrity(int id) {
        return tmdbInterface.getCelebrity(id, apiKey, "movie_credits,tagged_images,tv_credits,external_ids,images");
    }
}
