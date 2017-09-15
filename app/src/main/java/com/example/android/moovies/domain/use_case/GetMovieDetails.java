package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieDetails extends UseCase<MovieDetail, Integer> {

    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public GetMovieDetails(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<MovieDetail> createObservable(Integer movieId) {
        return movieRepositoryImpl.getMovieDetails(movieId);
    }
}
