package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CelebsDetail extends UseCase<Celebrity, Integer> {

    @Inject
    Repository movieRepository;

    @Inject
    CelebsDetail(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<Celebrity> createObservable(Integer integer) {
        return movieRepository.getCelebrity(integer);
    }

}
