package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CelebsDetail extends UseCase<Celebrity, Integer>{

    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public CelebsDetail(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<Celebrity> createObservable(Integer integer) {
        return movieRepositoryImpl.getCelebrity(integer);
    }
}
