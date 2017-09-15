package com.example.android.moovies.domain.models.account;

public class Rating {

    private int movieId;
    private Rated rated;

    public Rating(int movieId, Rated rated) {
        this.movieId = movieId;
        this.rated = rated;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Rated getRated() {
        return rated;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }
}
