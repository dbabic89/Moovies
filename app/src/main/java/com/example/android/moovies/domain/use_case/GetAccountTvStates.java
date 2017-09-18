package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.MtvRating;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAccountTvStates extends UseCase<MtvRating, Integer>{

    @Inject
    Repository repository;

    @Inject
    GetAccountTvStates(Repository repository) {
        this.repository = repository;
    }


    @Override
    Observable<MtvRating> createObservable(Integer tvId) {
        return repository.getAccountTvStates(tvId);
    }
}
