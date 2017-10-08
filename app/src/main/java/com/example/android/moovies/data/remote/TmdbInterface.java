package com.example.android.moovies.data.remote;

import com.example.android.moovies.domain.models.account.Account;
import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.PostToWatchlist;
import com.example.android.moovies.domain.models.account.Rated;
import com.example.android.moovies.domain.models.authentication.Session;
import com.example.android.moovies.domain.models.authentication.Token;
import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.tv.EpisodeDetail;
import com.example.android.moovies.domain.models.tv.SeasonDetail;
import com.example.android.moovies.domain.models.tv.TvDetail;
import com.example.android.moovies.domain.models.tv.TvListResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbInterface {

    //Movie lists

    @GET("movie/now_playing")
    Observable<MovieListResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex, @Query("region") String region);

    @GET("movie/upcoming")
    Observable<MovieListResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex, @Query("region") String region);

    @GET("movie/popular")
    Observable<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/top_rated")
    Observable<MovieListResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/{movie_id}/similar")
    Observable<MovieListResponse> getSimilarMovies(@Path("movie_id") int id, @Query("api_key") String apiKey, @Query("page") int pageIndex);

    @GET("collection/{collection_id}")
    Observable<CollectionDetail> getCollection(@Path("collection_id") int id, @Query("api_key") String apiKey);

    @GET("genre/{genre_id}/movies")
    Observable<MovieListResponse> getMoviesByGenre(@Path("genre_id") int genre_id, @Query("api_key") String apiKey, @Query("page") int pageIndex);


    //Movie details

    @GET("movie/{id}")
    Observable<MovieDetail> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey, @Query("append_to_response") String append);


    // Tv show list

    @GET("tv/airing_today")
    Observable<TvListResponse> getAiringTodayTv(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("tv/on_the_air")
    Observable<TvListResponse> getOnAirTv(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("tv/popular")
    Observable<TvListResponse> getPopularTv(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("tv/top_rated")
    Observable<TvListResponse> getTopRatedTv(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("tv/{tv_id}/similar")
    Observable<TvListResponse> getSimilarTv(@Path("tv_id") int id, @Query("api_key") String apiKey, @Query("page") int pageIndex);


    // Tv show details

    @GET("tv/{tv_id}")
    Observable<TvDetail> getTvDetail(@Path("tv_id") int id, @Query("api_key") String apiKey, @Query("append_to_response") String append);


    // Tv show season

    @GET("tv/{tv_id}/season/{season_number}")
    Observable<SeasonDetail> getSeason(@Path("tv_id") int id, @Path("season_number") int num, @Query("api_key") String apiKey);


    // Tv show episode details

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    Observable<EpisodeDetail> getEpisodeDetails(@Path("tv_id") int id, @Path("season_number") int se_num, @Path("episode_number") int ep_num, @Query("api_key") String apiKey, @Query("append_to_response") String append);

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/account_states")
    Observable<AccountStates> getEpisodeStates(@Path("tv_id") int id, @Path("season_number") int se_num, @Path("episode_number") int ep_num, @Query("api_key") String apiKey);

    @POST("tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating")
    Observable<PostResponse> addRatingEpisode(@Path("tv_id") int id, @Path("season_number") int se_num, @Path("episode_number") int ep_num, @Query("api_key") String apiKey, @Body Rated rated);

    @DELETE("tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating")
    Observable<PostResponse> deleteEpisodeRating(@Path("tv_id") int id, @Path("season_number") int se_num, @Path("episode_number") int ep_num, @Query("api_key") String apiKey);

    // Celebrity Detail

    @GET("person/{id}")
    Observable<Celebrity> getCelebrity(@Path("id") int id, @Query("api_key") String apiKey, @Query("append_to_response") String append);


    //Certification

    @GET("certification/movie/list")
    Observable<MovieDetail> getMovieCertification(@Query("api_key") String apiKey);

    @GET("certification/tv/list")
    Observable<MovieDetail> getTvCertification(@Query("api_key") String apiKey);


    //User authentication

    @GET("authentication/token/new")
    Call<Token> getToken(@Query("api_key") String apiKey);

    @GET("authentication/session/new")
    Call<Session> getSessionId(@Query("api_key") String apiKey, @Query("request_token") String token);


    //User account

    @GET("account")
    Observable<Account> getAccountDetail(@Query("api_key") String apiKey, @Query("session_id") String session_id);

    @POST("account/{account_id}/watchlist")
    Observable<PostResponse> addToWatchlist(@Path("account_id") int account_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Body PostToWatchlist postToWatchlist);


    @GET("movie/{movie_id}/account_states")
    Observable<AccountStates> getAccountStatesMovie(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("session_id") String session_id);

    @POST("movie/{movie_id}/rating")
    Observable<PostResponse> addMovieRating(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Body Rated movieRated);

    @DELETE("movie/{movie_id}/rating")
    Observable<PostResponse> deleteMovieRating(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("session_id") String session_id);

    @GET("account/{account_id}/rated/movies")
    Observable<MovieListResponse> getRatedMovies(@Path("account_id") int account_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Query("language") String language, @Query("page") int pageIndex);

    @GET("account/{account_id}/watchlist/movies")
    Observable<MovieListResponse> getWatchlistMovies(@Path("account_id") int account_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Query("language") String language, @Query("page") int pageIndex);


    @GET("tv/{tv_id}/account_states")
    Observable<AccountStates> getAccountStatesTv(@Path("tv_id") int movie_id, @Query("api_key") String apiKey, @Query("session_id") String session_id);

    @POST("tv/{tv_id}/rating")
    Observable<PostResponse> addTvRating(@Path("tv_id") int tv_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Body Rated movieRated);

    @DELETE("tv/{tv_id}/rating")
    Observable<PostResponse> deleteTvRating(@Path("tv_id") int tv_id, @Query("api_key") String apiKey, @Query("session_id") String session_id);

    @GET("account/{account_id}/rated/tv")
    Observable<TvListResponse> getRatedTvs(@Path("account_id") int account_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Query("language") String language, @Query("page") int pageIndex);

    @GET("account/{account_id}/watchlist/tv")
    Observable<TvListResponse> getWatchlistTvs(@Path("account_id") int account_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Query("language") String language, @Query("page") int pageIndex);


    // Search

    @GET("search/movie")
    Observable<MovieListResponse> searchMovies(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int pageIndex);
}
