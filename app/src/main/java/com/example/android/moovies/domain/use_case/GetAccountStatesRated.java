package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.AccountStatesRated;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAccountStatesRated extends UseCase<AccountStatesRated, Integer> {

    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public GetAccountStatesRated(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<AccountStatesRated> createObservable(Integer movieId) {
        return movieRepositoryImpl.getAccountStatesRated(movieId);
    }
}
