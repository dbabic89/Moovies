package com.example.android.moovies.data.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Keywords {

    @SerializedName("keywords")
    @Expose
    private List<Object> keywords = null;

    public List<Object> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }

}