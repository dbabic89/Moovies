package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.tv.TvListResponse;
import com.example.android.moovies.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetTvList extends UseCase<TvListResponse, List<Integer>>{

    @Inject
    Repository repository;

    private Observable<TvListResponse> observable;

    @Inject
    GetTvList(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<TvListResponse> createObservable(List<Integer> params) {

        switch (params.get(1)) {
            case 4:
                observable = repository.getAiringTodayTvs(params.get(0));
                break;
            case 5:
                observable = repository.getOnAirTvs(params.get(0));
                break;
            case 6:
                observable = repository.getPopularTvs(params.get(0));
                break;
            case 7:
                observable = repository.getTopRatedTvs(params.get(0));
                break;
            case 9:
                observable = repository.getSimilarTvs(params.get(2), params.get(0));
                break;
            case 13:
                observable = repository.getWatchlistTvs(params.get(0));
                break;
            case 14:
                observable = repository.getRatedTvs(params.get(0));
                break;
        }
        return observable;
    }
}
