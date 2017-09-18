package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.AccountStatesRated;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAccountStatesRated extends UseCase<AccountStatesRated, Integer> {

    @Inject
    Repository movieRepository;

    @Inject
    GetAccountStatesRated(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<AccountStatesRated> createObservable(Integer movieId) {
        return movieRepository.getAccountStatesRated(movieId);
    }

}
