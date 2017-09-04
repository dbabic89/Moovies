package com.example.android.moovies.data.remote;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.DataSource;
import com.example.android.moovies.domain.models.movie.MovieDetail;
import com.example.android.moovies.domain.models.movie.MovieListResponse;

import io.reactivex.Observable;

public class DataSourceNetwork implements DataSource{

    private final TmdbInterface tmdbInterface;

    public DataSourceNetwork(TmdbInterface tmdbInterface) {
        this.tmdbInterface = tmdbInterface;
    }

    @Override
    public Observable<MovieListResponse> getMovies(int page) {
        return tmdbInterface.getPopularMovies2(BuildConfig.TMDB_APIKEY, "en-US", page);
    }

    @Override
    public Observable<MovieDetail> getMovieDetais() {
        return null;
    }
}
