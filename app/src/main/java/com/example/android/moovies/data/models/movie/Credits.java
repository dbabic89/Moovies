package com.example.android.moovies.data.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Credits {

    @SerializedName("cast")
    @Expose
    private List<Object> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Object> crew = null;

    public List<Object> getCast() {
        return cast;
    }

    public void setCast(List<Object> cast) {
        this.cast = cast;
    }

    public List<Object> getCrew() {
        return crew;
    }

    public void setCrew(List<Object> crew) {
        this.crew = crew;
    }

}