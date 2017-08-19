package com.example.android.moovies.data.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Images {

    @SerializedName("backdrops")
    @Expose
    private List<Object> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<Object> posters = null;

    public List<Object> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Object> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Object> getPosters() {
        return posters;
    }

    public void setPosters(List<Object> posters) {
        this.posters = posters;
    }

}