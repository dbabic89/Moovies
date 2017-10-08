package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.tv.EpisodeDetail;
import com.example.android.moovies.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetEpisodeDetails extends UseCase<EpisodeDetail, List<Integer>> {

    @Inject
    Repository repository;

    @Inject
    GetEpisodeDetails(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<EpisodeDetail> createObservable(List<Integer> list) {
        return repository.getEpisodeDetails(list.get(0), list.get(1), list.get(2));
    }

}