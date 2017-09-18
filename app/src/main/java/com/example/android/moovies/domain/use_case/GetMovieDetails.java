package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieDetails extends UseCase<MovieDetail, Integer> {

    @Inject
    Repository Repository;

    @Inject
    GetMovieDetails(Repository Repository) {
        this.Repository = Repository;
    }

    @Override
    Observable<MovieDetail> createObservable(Integer movieId) {
        return Repository.getMovieDetails(movieId);
    }

}
