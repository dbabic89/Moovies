package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.account.PostMovieToWatchlist;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AddToWatchlist extends UseCase<PostResponse, PostMovieToWatchlist> {

    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public AddToWatchlist(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<PostResponse> createObservable(PostMovieToWatchlist movieToWatchlist) {
        return movieRepositoryImpl.addMovieToWatchlist(movieToWatchlist);
    }
}