package com.example.android.moovies.domain.models.tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Keywords {

    @SerializedName("results")
    @Expose
    private List<KeywordsResults> results = null;

    public List<KeywordsResults> getResults() {
        return results;
    }

    public void setResults(List<KeywordsResults> results) {
        this.results = results;
    }

}