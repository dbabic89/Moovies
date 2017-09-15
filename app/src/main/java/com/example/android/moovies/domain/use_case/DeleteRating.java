package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.Rating;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DeleteRating extends UseCase<PostResponse, Rating> {

    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public DeleteRating(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<PostResponse> createObservable(Rating rating) {
        return movieRepositoryImpl.deleteMovieRating(rating);
    }
}