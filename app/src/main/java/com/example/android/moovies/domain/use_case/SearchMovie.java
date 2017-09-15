package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.SearchQuery;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchMovie extends UseCase<MovieListResponse, SearchQuery> {

    @Inject
    MovieRepositoryImpl movieRepositoryImpl;

    @Inject
    public SearchMovie(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<MovieListResponse> createObservable(SearchQuery searchQuery) {
        return movieRepositoryImpl.searchMovie(searchQuery)
                .debounce(300, TimeUnit.MILLISECONDS);
    }
}
