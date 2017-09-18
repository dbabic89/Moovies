package com.example.android.moovies.domain.models.celebrity;

import java.io.Serializable;

public class CelebsCredits implements Serializable {

    private Posters moviePosters;
    private Posters tvPosters;

    public CelebsCredits(Posters moviePosters, Posters tvPosters) {
        this.moviePosters = moviePosters;
        this.tvPosters = tvPosters;
    }

    public Posters getMoviePosters() {
        return moviePosters;
    }

    public void setMoviePosters(Posters moviePosters) {
        this.moviePosters = moviePosters;
    }

    public Posters getTvPosters() {
        return tvPosters;
    }

    public void setTvPosters(Posters tvPosters) {
        this.tvPosters = tvPosters;
    }

}
