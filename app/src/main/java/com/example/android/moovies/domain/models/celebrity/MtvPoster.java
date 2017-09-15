package com.example.android.moovies.domain.models.celebrity;

import java.io.Serializable;

public class MtvPoster implements Serializable{

    private int id;
    private String posterPath;

    public MtvPoster(int id, String posterPath) {
        this.id = id;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
