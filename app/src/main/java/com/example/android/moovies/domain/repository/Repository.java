package com.example.android.moovies.domain.repository;

import com.example.android.moovies.data.DataSource;
import com.example.android.moovies.data.remote.DataSourceNetwork;
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
    public Observable<SearchResults> searchMovie(SearchQuery searchQuery) {
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
    public Observable<SeasonDetail> getSeason(int tvId, int num) {
        return dataSourceNetwork.getSeason(tvId, num);
    }

    @Override
    public Observable<EpisodeDetail> getEpisodeDetails(int tvId, int s_num, int e_num) {
        return dataSourceNetwork.getEpisodeDetails(tvId, s_num, e_num);
    }

    @Override
    public Observable<AccountStates> getEpisodeStates(int tvId, int s_num, int e_num) {
        return dataSourceNetwork.getEpisodeStates(tvId, s_num, e_num);
    }

    @Override
    public Observable<PostResponse> addEpisodeRating(EpisodeRating episodeRating) {
        return dataSourceNetwork.addEpisodeRating(episodeRating);
    }

    @Override
    public Observable<PostResponse> deleteEpisodeRating(EpisodeRating episodeRating) {
        return dataSourceNetwork.deleteEpisodeRating(episodeRating);
    }

    @Override
    public Observable<AccountStates> getAccountStatesTv(int tvId) {
        return dataSourceNetwork.getAccountStatesTv(tvId);
    }

    @Override
    public Observable<PostResponse> addTvRating(MtvRating mtvRating) {
        return dataSourceNetwork.addTvRating(mtvRating);
    }

    @Override
    public Observable<PostResponse> deleteTvRating(MtvRating mtvRating) {
        return dataSourceNetwork.deleteTvRating(mtvRating);
    }

    @Override
    public Observable<TvListResponse> getRatedTvs(int page) {
        return dataSourceNetwork.getRatedTvs(page);
    }

    @Override
    public Observable<TvListResponse> getWatchlistTvs(int page) {
        return dataSourceNetwork.getWatchlistTvs(page);
    }

    @Override
    public Observable<MovieDetail> getMovieDetails(int movieId) {
        return dataSourceNetwork.getMovieDetails(movieId);
    }

    @Override
    public Observable<AccountStates> getAccountStatesMovie(int movieId) {
        return dataSourceNetwork.getAccountStatesMovie(movieId);
    }

    @Override
    public Observable<PostResponse> addToWatchlist(PostToWatchlist toWatchlist) {
        return dataSourceNetwork.addToWatchlist(toWatchlist);
    }

    @Override
    public Observable<PostResponse> addMovieRating(MtvRating mtvRating) {
        return dataSourceNetwork.addMovieRating(mtvRating);
    }

    @Override
    public Observable<PostResponse> deleteMovieRating(MtvRating mtvRating) {
        return dataSourceNetwork.deleteMovieRating(mtvRating);
    }

    @Override
    public Observable<MovieListResponse> getRatedMovies(int page) {
        return dataSourceNetwork.getRatedMovies(page);
    }

    @Override
    public Observable<MovieListResponse> getWatchlistMovies(int page) {
        return dataSourceNetwork.getWatchlistMovies(page);
    }

    @Override
    public Observable<Celebrity> getCelebrity(int id) {
        return dataSourceNetwork.getCelebrity(id);
    }
}
