package com.example.android.moovies.domain.models.account;

public class Rating {

    private int id;
    private Rated rated;

    public Rating(int id, Rated rated) {
        this.id = id;
        this.rated = rated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rated getRated() {
        return rated;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }
}
