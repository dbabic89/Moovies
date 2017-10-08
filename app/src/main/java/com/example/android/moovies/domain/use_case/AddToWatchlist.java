package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.PostToWatchlist;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AddToWatchlist extends UseCase<PostResponse, PostToWatchlist> {

    @Inject
    Repository repository;

    @Inject
    AddToWatchlist(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<PostResponse> createObservable(PostToWatchlist toWatchlist) {
        return repository.addToWatchlist(toWatchlist);
    }

}