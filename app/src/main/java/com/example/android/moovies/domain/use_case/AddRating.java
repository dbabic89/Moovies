package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.Rating;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;


public class AddRating extends UseCase<PostResponse, Rating> {

    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public AddRating(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<PostResponse> createObservable(Rating rating) {
        return movieRepositoryImpl.addMovieRating(rating);
    }
}