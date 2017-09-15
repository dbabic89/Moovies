package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.AccountStatesRating;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAccountStatesRating extends UseCase<AccountStatesRating, Integer> {

    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public GetAccountStatesRating(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<AccountStatesRating> createObservable(Integer movieId) {
        return movieRepositoryImpl.getAccountStatesRating(movieId);
    }
}
