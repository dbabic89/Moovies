package com.example.android.moovies.data.remote;

import com.example.android.moovies.data.models.account.Account;
import com.example.android.moovies.data.models.account.AccountStatesRated;
import com.example.android.moovies.data.models.account.AccountStatesRating;
import com.example.android.moovies.data.models.account.PostMovieToWatchlist;
import com.example.android.moovies.data.models.account.PostResponse;
import com.example.android.moovies.data.models.account.Rated;
import com.example.android.moovies.data.models.authentication.Session;
import com.example.android.moovies.data.models.authentication.Token;
import com.example.android.moovies.data.models.movie.CollectionDetail;
import com.example.android.moovies.data.models.movie.MovieDetail;
import com.example.android.moovies.data.models.movie.MovieListResponse;

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
    Call<MovieListResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/upcoming")
    Call<MovieListResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/top_rated")
    Call<MovieListResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("genre/{genre_id}/movies")
    Observable<MovieListResponse> getMoviesByGenre(@Path("genre_id") int genre_id, @Query("api_key") String apiKey, @Query("page") int pageIndex);


    @GET("movie/now_playing")
    Observable<MovieListResponse> getNowPlayingMovies2(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/upcoming")
    Observable<MovieListResponse> getUpcomingMovies2(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/popular")
    Observable<MovieListResponse> getPopularMovies2(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/top_rated")
    Observable<MovieListResponse> getTopRatedMovies2(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/{movie_id}/similar")
    Observable<MovieListResponse> getSimilar(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("collection/{collection_id}")
    Observable<CollectionDetail> getCollection(@Path("collection_id") int id, @Query("api_key") String apiKey);


    //Movie details

    @GET("movie/{id}")
    Observable<MovieDetail> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey, @Query("append_to_response") String append);

    @GET("movie/{movie_id}/release_dates")
    Observable<MovieDetail> getMovieReleaseDateAndCertification(@Path("movie_id") int id, @Query("api_key") String apiKey);

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

    @GET("movie/{movie_id}/account_states")
    Observable<AccountStatesRated> getAccountStatesRated(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("session_id") String session_id);

    @GET("movie/{movie_id}/account_states")
    Observable<AccountStatesRating> getAccountStatesRating(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("session_id") String session_id);

    @POST("account/{account_id}/watchlist")
    Call<PostResponse> addMovieToWatchlist (@Path("account_id") int account_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Body PostMovieToWatchlist movieToWatchlist);

    @POST("movie/{movie_id}/rating")
    Call<PostResponse> addMovieRating (@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("session_id") String session_id, @Body Rated movieRated);

    @DELETE("movie/{movie_id}/rating")
    Call<PostResponse> deleteMovieRating(@Path("movie_id") int movie_id, @Query("api_key") String apiKey, @Query("session_id") String session_id);

}
