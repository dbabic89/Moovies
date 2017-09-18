package com.example.android.moovies.domain.models.tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentRatings {

    @SerializedName("results")
    @Expose
    private List<ContentRatingResult> results = null;

    public List<ContentRatingResult> getResults() {
        return results;
    }

    public void setResults(List<ContentRatingResult> results) {
        this.results = results;
    }

}