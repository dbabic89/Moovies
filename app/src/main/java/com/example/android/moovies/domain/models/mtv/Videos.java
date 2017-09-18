package com.example.android.moovies.domain.models.mtv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Videos implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Video> results = null;

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

}