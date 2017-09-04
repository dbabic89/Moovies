package com.example.android.moovies.domain.repository;

import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;

import io.reactivex.Observable;

public interface MovieRepository {

    Observable<MovieListResponse> getMovies(int page);

    Observable<MovieDetail> getMovieDetails();
}
