package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAccountStatesMovie extends UseCase<AccountStates, Integer> {

    @Inject
    Repository movieRepository;

    @Inject
    GetAccountStatesMovie(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<AccountStates> createObservable(Integer movieId) {
        return movieRepository.getAccountStatesMovie(movieId);
    }

}
