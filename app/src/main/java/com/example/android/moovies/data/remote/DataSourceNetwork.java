package com.example.android.moovies.data.remote;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.DataSource;
import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.domain.models.search.SearchQuery;
import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.models.account.EpisodeRating;
import com.example.android.moovies.domain.models.account.MtvRating;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.PostToWatchlist;
import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.search.SearchResults;
import com.example.android.moovies.domain.models.tv.EpisodeDetail;
import com.example.android.moovies.domain.models.tv.SeasonDetail;
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
    public Observable<SearchResults> searchMovie(SearchQuery searchQuery) {
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
        return tmdbInterface.getTvDetail(tvId, apiKey, "images,videos,credits,similar,reviews,content_ratings,keywords,alternative_titles");
    }

    @Override
    public Observable<SeasonDetail> getSeason(int tvId, int num) {
        return tmdbInterface.getSeason(tvId, num, apiKey);
    }

    @Override
    public Observable<EpisodeDetail> getEpisodeDetails(int tvId, int s_num, int e_num) {
        return tmdbInterface.getEpisodeDetails(tvId, s_num, e_num, apiKey, "images,videos");
    }

    @Override
    public Observable<AccountStates> getEpisodeStates(int tvId, int s_num, int e_num) {
        return tmdbInterface.getEpisodeStates(tvId, s_num, e_num, apiKey, sessionId);
    }

    @Override
    public Observable<PostResponse> addEpisodeRating(EpisodeRating rating) {
        return tmdbInterface.addRatingEpisode(rating.getId(), rating.getS_num(), rating.getE_num(), apiKey, sessionId, rating.getRated());
    }

    @Override
    public Observable<PostResponse> deleteEpisodeRating(EpisodeRating rating) {
        return tmdbInterface.deleteEpisodeRating(rating.getId(), rating.getS_num(), rating.getE_num(), apiKey, sessionId);
    }

    @Override
    public Observable<AccountStates> getAccountStatesTv(int tvId) {
        return tmdbInterface.getAccountStatesTv(tvId, apiKey, sessionId);
    }

    @Override
    public Observable<PostResponse> addTvRating(MtvRating mtvRating) {
        return tmdbInterface.addTvRating(mtvRating.getId(), apiKey, sessionId, mtvRating.getRated());
    }

    @Override
    public Observable<PostResponse> deleteTvRating(MtvRating mtvRating) {
        return tmdbInterface.deleteTvRating(mtvRating.getId(), apiKey, sessionId);
    }

    @Override
    public Observable<TvListResponse> getRatedTvs(int page) {
        return tmdbInterface.getRatedTvs(accoutId, apiKey, sessionId, language,page);
    }

    @Override
    public Observable<TvListResponse> getWatchlistTvs(int page) {
        return tmdbInterface.getWatchlistTvs(accoutId, apiKey, sessionId, language,page);
    }

    @Override
    public Observable<MovieDetail> getMovieDetails(int movieId) {
        return tmdbInterface.getMovieDetails(movieId, apiKey, "images,videos,credits,similar,reviews,keywords,releases");
    }

    @Override
    public Observable<AccountStates> getAccountStatesMovie(int movieId) {
        return tmdbInterface.getAccountStatesMovie(movieId, apiKey, sessionId);
    }

    @Override
    public Observable<PostResponse> addToWatchlist(PostToWatchlist movieToWatchlist) {
        return tmdbInterface.addToWatchlist(accoutId, apiKey, sessionId, movieToWatchlist);
    }

    @Override
    public Observable<PostResponse> addMovieRating(MtvRating mtvRating) {
        return tmdbInterface.addMovieRating(mtvRating.getId(), apiKey, sessionId, mtvRating.getRated());
    }

    @Override
    public Observable<PostResponse> deleteMovieRating(MtvRating mtvRating) {
        return tmdbInterface.deleteMovieRating(mtvRating.getId(), apiKey, sessionId);
    }

    @Override
    public Observable<MovieListResponse> getRatedMovies(int page) {
        return tmdbInterface.getRatedMovies(accoutId, apiKey, sessionId, language, page);
    }

    @Override
    public Observable<MovieListResponse> getWatchlistMovies(int page) {
        return tmdbInterface.getWatchlistMovies(accoutId, apiKey, sessionId, language, page);
    }

    @Override
    public Observable<Celebrity> getCelebrity(int id) {
        return tmdbInterface.getCelebrity(id, apiKey, "movie_credits,tagged_images,tv_credits,external_ids,images");
    }
}
