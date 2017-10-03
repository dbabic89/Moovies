package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.example.android.moovies.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GetMovieList extends UseCase<MovieListResponse, List<Integer>> {

    @Inject
    Repository repository;

    private Observable<MovieListResponse> observable;

    @Inject
    public GetMovieList(Repository repository) {
        this.repository = repository;
    }

    @Override
    Observable<MovieListResponse> createObservable(List<Integer> params) {

        switch (params.get(1)) {
            case 0:
                observable = repository.getNowPlayingMovies(params.get(0));
                break;
            case 1:
                observable = repository.getUpcomingMovies(params.get(0));
                break;
            case 2:
                observable = repository.getPopularMovies(params.get(0));
                break;
            case 3:
                observable = repository.getTopRatedMovies(params.get(0));
                break;
            case 8:
                observable = repository.getSimilarMovies(params.get(2), params.get(0));
                break;
            case 11:
                observable = repository.getRatedMovies(params.get(0));
                break;
            case 12:
                observable = repository.getWatchlistMovies(params.get(0));
                break;
        }
        return observable;
    }

}
