package com.example.android.moovies.domain.models.mtv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Images implements Serializable {

    @SerializedName("backdrops")
    @Expose
    private List<Image> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<Image> posters = null;
    @SerializedName("stills")
    @Expose
    private List<Image> stills = null;


    public List<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Image> getPosters() {
        return posters;
    }

    public void setPosters(List<Image> posters) {
        this.posters = posters;
    }

    public List<Image> getStills() {
        return stills;
    }

    public void setStills(List<Image> stills) {
        this.stills = stills;
    }

}