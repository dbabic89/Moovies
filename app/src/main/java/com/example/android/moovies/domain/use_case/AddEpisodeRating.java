package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.EpisodeRating;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AddEpisodeRating extends UseCase<PostResponse, EpisodeRating> {

    @Inject
    Repository repository;

    @Inject
    AddEpisodeRating(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<PostResponse> createObservable(EpisodeRating rating) {
        return repository.addEpisodeRating(rating);
    }

}