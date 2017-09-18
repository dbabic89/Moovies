package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.PostMovieToWatchlist;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AddToWatchlist extends UseCase<PostResponse, PostMovieToWatchlist> {

    @Inject
    Repository movieRepository;

    @Inject
    AddToWatchlist(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<PostResponse> createObservable(PostMovieToWatchlist movieToWatchlist) {
        return movieRepository.addMovieToWatchlist(movieToWatchlist);
    }

}