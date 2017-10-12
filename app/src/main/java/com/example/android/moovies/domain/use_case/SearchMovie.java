package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.search.SearchQuery;
import com.example.android.moovies.domain.models.search.SearchResults;
import com.example.android.moovies.domain.repository.Repository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchMovie extends UseCase<SearchResults, SearchQuery> {

    @Inject
    Repository repository;

    @Inject
    public SearchMovie(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<SearchResults> createObservable(SearchQuery searchQuery) {
        return repository.searchMovie(searchQuery)
                .debounce(300, TimeUnit.MILLISECONDS);
    }

}
