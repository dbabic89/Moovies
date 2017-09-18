package com.example.android.moovies.domain.use_case;

import android.util.Log;

import com.example.android.moovies.domain.models.tv.TvListResponse;
import com.example.android.moovies.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetTvList extends UseCase<TvListResponse, List<Integer>>{

    @Inject
    Repository movieRepository;

    private Observable<TvListResponse> observable;

    @Inject
    public GetTvList(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<TvListResponse> createObservable(List<Integer> params) {

        switch (params.get(1)) {
            case 4:
                observable = movieRepository.getAiringTodayTvs(params.get(0));
                break;
            case 5:
                observable = movieRepository.getOnAirTvs(params.get(0));
                break;
            case 6:
                observable = movieRepository.getPopularTvs(params.get(0));
                break;
            case 7:
                observable = movieRepository.getTopRatedTvs(params.get(0));
                break;
            case 9:
                observable = movieRepository.getSimilarTvs(params.get(2), params.get(0));
                break;
        }
        return observable;
    }
}
