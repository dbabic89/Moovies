package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieList extends UseCase<MovieListResponse, List<Integer>> {

    @Inject
    Repository movieRepository;

    private Observable<MovieListResponse> observable;

    @Inject
    public GetMovieList(Repository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<MovieListResponse> createObservable(List<Integer> params) {

        switch (params.get(1)) {
            case 0:
                observable = movieRepository.getNowPlayingMovies(params.get(0));
                break;
            case 1:
                observable = movieRepository.getUpcomingMovies(params.get(0));
                break;
            case 2:
                observable = movieRepository.getPopularMovies(params.get(0));
                break;
            case 3:
                observable = movieRepository.getTopRatedMovies(params.get(0));
                break;
            case 8:
                observable = movieRepository.getSimilarMovies(params.get(2), params.get(0));
                break;
            case 11:
                observable = movieRepository.getRatedMovies(params.get(0));
                break;
            case 12:
                observable = movieRepository.getMovieWatchlist(params.get(0));
                break;
        }
        return observable;
    }

}
