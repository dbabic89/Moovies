package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.tv.TvDetail;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetTvDetails extends UseCase<TvDetail, Integer>{

    @Inject
    Repository Repository;

    @Inject
    GetTvDetails(Repository Repository) {
        this.Repository = Repository;
    }

    @Override
    Observable<TvDetail> createObservable(Integer integer) {
        return Repository.getTvDetail(integer);
    }

}
