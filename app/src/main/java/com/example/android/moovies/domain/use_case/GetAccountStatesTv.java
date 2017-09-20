package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAccountStatesTv extends UseCase<AccountStates, Integer>{

    @Inject
    Repository repository;

    @Inject
    GetAccountStatesTv(Repository repository) {
        this.repository = repository;
    }


    @Override
    Observable<AccountStates> createObservable(Integer tvId) {
        return repository.getAccountStatesTv(tvId);
    }
}
