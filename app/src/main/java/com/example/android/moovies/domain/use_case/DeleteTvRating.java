package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.Rating;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DeleteTvRating extends UseCase<PostResponse, Rating> {

    @Inject
    Repository movieRepository;

    @Inject
    DeleteTvRating(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<PostResponse> createObservable(Rating rating) {
        return movieRepository.deleteTvRating(rating);
    }

}