package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieDetails extends UseCase<MovieDetail, Integer> {

    @Inject
    Repository repository;

    @Inject
    GetMovieDetails(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<MovieDetail> createObservable(Integer movieId) {
        return repository.getMovieDetails(movieId);
    }

}
