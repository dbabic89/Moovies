package com.example.android.moovies.domain.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Reviews implements Serializable{

    @SerializedName("page")
    @Expose
    public int page;
    @SerializedName("results")
    @Expose
    public List<Review> results = null;
    @SerializedName("total_pages")
    @Expose
    public int totalPages;
    @SerializedName("total_results")
    @Expose
    public int totalResults;

    public Reviews(List<Review> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

}