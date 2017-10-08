package com.example.android.moovies.data;

import com.example.android.moovies.domain.models.SearchQuery;
import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.models.account.EpisodeRating;
import com.example.android.moovies.domain.models.account.MtvRating;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.PostToWatchlist;
import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.tv.EpisodeDetail;
import com.example.android.moovies.domain.models.tv.SeasonDetail;
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

    Observable<SeasonDetail> getSeason(int tvId, int num);

    Observable<EpisodeDetail> getEpisodeDetails(int tvId, int s_num, int e_num);

    Observable<AccountStates> getEpisodeStates(int tvId, int s_num, int e_num);

    Observable<PostResponse> addEpisodeRating(EpisodeRating episodeRating);

    Observable<PostResponse> deleteEpisodeRating(EpisodeRating episodeRating);


    Observable<AccountStates> getAccountStatesTv(int movieId);

    Observable<PostResponse> addTvRating(MtvRating mtvRating);

    Observable<PostResponse> deleteTvRating(MtvRating mtvRating);

    Observable<TvListResponse> getRatedTvs(int page);

    Observable<TvListResponse> getWatchlistTvs(int page);


    Observable<AccountStates> getAccountStatesMovie(int movieId);

    Observable<PostResponse> addToWatchlist(PostToWatchlist postToWatchlist);

    Observable<PostResponse> addMovieRating(MtvRating mtvRating);

    Observable<PostResponse> deleteMovieRating(MtvRating mtvRating);

    Observable<MovieListResponse> getRatedMovies(int page);

    Observable<MovieListResponse> getWatchlistMovies(int page);


    Observable<Celebrity> getCelebrity(int id);
}
