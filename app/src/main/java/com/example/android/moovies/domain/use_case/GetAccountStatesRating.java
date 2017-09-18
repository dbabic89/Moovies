package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.AccountStatesRating;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAccountStatesRating extends UseCase<AccountStatesRating, Integer> {

    @Inject
    Repository movieRepository;

    @Inject
    GetAccountStatesRating(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<AccountStatesRating> createObservable(Integer movieId) {
        return movieRepository.getAccountStatesRating(movieId);
    }

}
