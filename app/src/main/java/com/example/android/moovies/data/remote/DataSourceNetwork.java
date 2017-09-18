package com.example.android.moovies.data.remote;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.DataSource;
import com.example.android.moovies.data.local.SharedPreferencesManager;
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

public class DataSourceNetwork implements DataSource {

    @Inject
    TmdbInterface tmdbInterface;
    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    private String apiKey = BuildConfig.TMDB_APIKEY;
    private String sessionId;
    private String language = "en-US";
    private String region = "US";
    private String appendMovies = "images,videos,credits,similar,reviews,keywords,releases";
    private String appendTvs = "images,videos,credits,similar,reviews,content_ratings,keywords,alternative_titles";
    private String appendCelebs = "movie_credits,tagged_images,tv_credits,external_ids,images";
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
        return tmdbInterface.getNowPlayingMovies(apiKey, language, page, region);
    }

    @Override
    public Observable<MovieListResponse> getUpcomingMovies(int page) {
        return tmdbInterface.getUpcomingMovies(apiKey, language, page, region);
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
    public Observable<MovieListResponse> getSimilarMovies(int movieId, int page) {
        return tmdbInterface.getSimilarMovies(movieId, apiKey, page);
    }

    @Override
    public Observable<MovieListResponse> searchMovie(SearchQuery searchQuery) {
        return tmdbInterface.searchMovies(apiKey, searchQuery.getQuery(), searchQuery.getPage());
    }

    @Override
    public Observable<CollectionDetail> getCollection(int movieId) {
        return tmdbInterface.getCollection(movieId, apiKey);
    }

    @Override
    public Observable<TvListResponse> getAiringTodayTvs(int page) {
        return tmdbInterface.getAiringTodayTv(apiKey, language, page);
    }

    @Override
    public Observable<TvListResponse> getOnAirTvs(int page) {
        return tmdbInterface.getOnAirTv(apiKey, language, page);
    }

    @Override
    public Observable<TvListResponse> getPopularTvs(int page) {
        return tmdbInterface.getPopularTv(apiKey, language, page);
    }

    @Override
    public Observable<TvListResponse> getTopRatedTvs(int page) {
        return tmdbInterface.getTopRatedTv(apiKey, language, page);
    }

    @Override
    public Observable<TvListResponse> getSimilarTvs(int tvId, int page) {
        return tmdbInterface.getSimilarTv(tvId, apiKey, page);
    }

    @Override
    public Observable<TvDetail> getTvDetail(int tvId) {
        return tmdbInterface.getTvDetail(tvId, apiKey, appendTvs);
    }

    @Override
    public Observable<MtvRating> getAccountTvStates(int tvId) {
        return tmdbInterface.getAccountTvStates(tvId, apiKey, sessionId);
    }

    @Override
    public Observable<MovieDetail> getMovieDetails(int movieId) {
        return tmdbInterface.getMovieDetails(movieId, apiKey, appendMovies);
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
        return tmdbInterface.getCelebrity(id, apiKey, appendCelebs);
    }
}
