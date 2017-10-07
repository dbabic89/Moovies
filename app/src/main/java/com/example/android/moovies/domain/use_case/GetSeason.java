package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.tv.SeasonDetail;
import com.example.android.moovies.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetSeason extends  UseCase<SeasonDetail, List<Integer>>{

    @Inject
    Repository repository;

    private Observable<SeasonDetail> observable;

    @Inject
    public GetSeason(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<SeasonDetail> createObservable(List<Integer> integers) {
        return observable = repository.getSeason(integers.get(0), integers.get(1));
    }
}
