package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieList extends  UseCase<MovieListResponse, Integer>{

    private MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public GetMovieList(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<MovieListResponse> createObservable(Integer page) {
        return movieRepositoryImpl.getMovies(page);
    }
}
