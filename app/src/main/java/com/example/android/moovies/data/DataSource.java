package com.example.android.moovies.data;

import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;

import io.reactivex.Observable;

public interface DataSource {

    Observable<MovieListResponse> getMovies(int page);

    Observable<MovieDetail> getMovieDetais();
}
