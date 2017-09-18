package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieCollectionList extends UseCase<CollectionDetail, Integer> {


    @Inject
    Repository movieRepository;

    @Inject
    GetMovieCollectionList(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<CollectionDetail> createObservable(Integer movieId) {
        return movieRepository.getCollection(movieId);
    }

}
