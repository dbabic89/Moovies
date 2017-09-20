package com.example.android.moovies.data;

import com.example.android.moovies.domain.models.SearchQuery;
import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.models.account.PostToWatchlist;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.Rating;
import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.tv.TvDetail;
import com.example.android.moovies.domain.models.tv.TvListResponse;

import io.reactivex.Observable;

public interface DataSource {

    Observable<MovieListResponse> getNowPlayingMovies(int page);

    Observable<MovieListResponse> getUpcomingMovies(int page);

    Observable<MovieListResponse> getPopularMovies(int page);

    Observable<MovieListResponse> getTopRatedMovies(int page);

    Observable<MovieListResponse> getSimilarMovies(int movieId, int page);

    Observable<MovieListResponse> searchMovie(SearchQuery searchQuery);

    Observable<MovieDetail> getMovieDetails(int movieId);


    Observable<CollectionDetail> getCollection(int movieId);


    Observable<TvListResponse> getAiringTodayTvs(int page);

    Observable<TvListResponse> getOnAirTvs(int page);

    Observable<TvListResponse> getPopularTvs(int page);

    Observable<TvListResponse> getTopRatedTvs(int page);

    Observable<TvListResponse> getSimilarTvs(int tvId, int page);

    Observable<TvDetail> getTvDetail(int tvId);


    Observable<AccountStates> getAccountStatesTv(int movieId);

    Observable<PostResponse> addTvRating(Rating rating);

    Observable<PostResponse> deleteTvRating(Rating rating);

    Observable<TvListResponse> getRatedTvs(int page);

    Observable<TvListResponse> getWatchlistTvs(int page);


    Observable<AccountStates> getAccountStatesMovie(int movieId);

    Observable<PostResponse> addToWatchlist(PostToWatchlist postToWatchlist);

    Observable<PostResponse> addMovieRating(Rating rating);

    Observable<PostResponse> deleteMovieRating(Rating rating);

    Observable<MovieListResponse> getRatedMovies(int page);

    Observable<MovieListResponse> getWatchlistMovies(int page);


    Observable<Celebrity> getCelebrity(int id);
}
