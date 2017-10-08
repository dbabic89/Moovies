package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAccountStatesEpisode extends UseCase<AccountStates, List<Integer>> {

    @Inject
    Repository repository;

    @Inject
    GetAccountStatesEpisode(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<AccountStates> createObservable(List<Integer> list) {
        return repository.getEpisodeStates(list.get(0), list.get(1), list.get(2));
    }

}
