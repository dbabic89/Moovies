package com.example.android.moovies.data.remote;

import android.accounts.Account;

import com.example.android.moovies.data.models.MovieGenreResponse;
import com.example.android.moovies.data.models.authentication.RequestTokenResponse;
import com.example.android.moovies.data.models.movie.MovieListResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbInterface {

    @GET("movie/now_playing")
    Call<MovieListResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/upcoming")
    Call<MovieListResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/top_rated")
    Call<MovieListResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("genre/{genre_id}/movies")
    public Observable<MovieListResponse> getMoviesByGenre(@Path("genre_id") int genre_id, @Query("api_key") String apiKey, @Query("page") int pageIndex);


    @GET("genre/movie/list")
    public Observable<MovieGenreResponse> getMovieGenresList(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Observable<MovieListResponse> getNowPlayingMovies2(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/upcoming")
    Observable<MovieListResponse> getUpcomingMovies2(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/popular")
    Observable<MovieListResponse> getPopularMovies2(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/top_rated")
    Observable<MovieListResponse> getTopRatedMovies2(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);


    //User authentication

    @POST("auth/request_token")
    Call<RequestTokenResponse> requestToken(@Header("Authorization:") String authorization, @Header("Content-Type:") String contentType);

    @Headers({"Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2ZiZmIzM2JjZGIzYjE4ZjJkYzY4YTM0MzgyNGEyMiIsInN1YiI6IjU4Zjg4YTUwYzNhMzY4NzVmMDAwNjc3NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gAGNBXdD0Xf53WusYqEU4ioLAp0vCWmdUhyCjr8qqHo",
    "Content-Type:application/json;charset=utf-8"})
    @POST("auth/request_token")
    Call<RequestTokenResponse> requestToken2();

    //User account

    @GET("account")
    Observable<Account> getAccountDetail(@Query("api_key") String apiKey, @Query("session_id") String session_id);

}
