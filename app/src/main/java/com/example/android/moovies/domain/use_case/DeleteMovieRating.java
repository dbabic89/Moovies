package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.Rating;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DeleteMovieRating extends UseCase<PostResponse, Rating> {

    @Inject
    Repository repository;

    @Inject
    DeleteMovieRating(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<PostResponse> createObservable(Rating rating) {
        return repository.deleteMovieRating(rating);
    }

}