package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.MtvRating;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DeleteMovieRating extends UseCase<PostResponse, MtvRating> {

    @Inject
    Repository repository;

    @Inject
    DeleteMovieRating(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<PostResponse> createObservable(MtvRating mtvRating) {
        return repository.deleteMovieRating(mtvRating);
    }

}