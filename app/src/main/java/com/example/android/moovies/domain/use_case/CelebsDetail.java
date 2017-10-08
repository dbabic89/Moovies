package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CelebsDetail extends UseCase<Celebrity, Integer> {

    @Inject
    Repository repository;

    @Inject
    CelebsDetail(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<Celebrity> createObservable(Integer integer) {
        return repository.getCelebrity(integer);
    }

}
