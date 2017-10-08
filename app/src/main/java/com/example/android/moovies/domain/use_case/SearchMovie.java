package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.SearchQuery;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.repository.Repository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchMovie extends UseCase<MovieListResponse, SearchQuery> {

    @Inject
    Repository repository;

    @Inject
    public SearchMovie(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<MovieListResponse> createObservable(SearchQuery searchQuery) {
        return repository.searchMovie(searchQuery)
                .debounce(300, TimeUnit.MILLISECONDS);
    }

}
