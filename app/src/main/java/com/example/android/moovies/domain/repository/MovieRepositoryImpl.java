package com.example.android.moovies.domain.repository;

import com.example.android.moovies.data.remote.DataSourceNetwork;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieRepositoryImpl implements MovieRepository{

    private DataSourceNetwork dataSourceNetwork;

    @Inject
    public MovieRepositoryImpl(DataSourceNetwork dataSourceNetwork) {
        this.dataSourceNetwork = dataSourceNetwork;
    }

    @Override
    public Observable<MovieListResponse> getMovies(int page) {
        return dataSourceNetwork.getMovies(page);
    }

    @Override
    public Observable<MovieDetail> getMovieDetails() {
        return null;
    }
}
