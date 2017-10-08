package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieCollectionList extends UseCase<CollectionDetail, Integer> {


    @Inject
    Repository repository;

    @Inject
    GetMovieCollectionList(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<CollectionDetail> createObservable(Integer movieId) {
        return repository.getCollection(movieId);
    }

}
