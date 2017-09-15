package com.example.android.moovies.domain.use_case;

import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;
import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.example.android.moovies.domain.repository.MovieRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieList extends  UseCase<MovieListResponse, List<Integer>>{

    @Inject MovieRepositoryImpl movieRepositoryImpl;

    private Observable<MovieListResponse> observable;
    private List<MovieListResult> observable2;
    private Observable<MovieDetail> detailObservable;

    @Inject
    public GetMovieList(MovieRepositoryImpl movieRepositoryImpl) {
        this.movieRepositoryImpl = movieRepositoryImpl;
    }

    @Override
    Observable<MovieListResponse> createObservable(List<Integer> params) {

        switch (params.get(1)){
            case 0:
                observable = movieRepositoryImpl.getNowPlayingMovies(params.get(0));
                break;
            case 1:
                observable = movieRepositoryImpl.getUpcomingMovies(params.get(0));
                break;
            case 2:
                observable = movieRepositoryImpl.getPopularMovies(params.get(0));
                break;
            case 3:
                observable =  movieRepositoryImpl.getTopRatedMovies(params.get(0));
                break;
            case 4:
                observable =  movieRepositoryImpl.getSimilar(params.get(2));
                break;
            case 6:
                observable =  movieRepositoryImpl.getRatedMovies(params.get(0));
                break;
            case 7:
                observable =  movieRepositoryImpl.getMovieWatchlist(params.get(0));
                break;
        }
        return observable;
    }
}
