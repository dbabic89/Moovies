package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieCollectionList extends UseCase<CollectionDetail, Integer> {


    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    GetMovieCollectionList(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<CollectionDetail> createObservable(Integer movieId) {
        return movieRepositoryImpl.getCollection(movieId);
    }
}
