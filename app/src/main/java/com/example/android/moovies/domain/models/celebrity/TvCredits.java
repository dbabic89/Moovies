package com.example.android.moovies.domain.models.celebrity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvCredits {

    @SerializedName("cast")
    @Expose
    private List<AsCast> cast;
    @SerializedName("crew")
    @Expose
    private List<AsCrew> crew;

    public List<AsCast> getCast() {
        return cast;
    }

    public void setCast(List<AsCast> cast) {
        this.cast = cast;
    }

    public List<AsCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<AsCrew> crew) {
        this.crew = crew;
    }

}