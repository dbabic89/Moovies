package com.example.android.moovies.data.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Releases {

    @SerializedName("countries")
    @Expose
    private List<Object> countries = null;

    public List<Object> getCountries() {
        return countries;
    }

    public void setCountries(List<Object> countries) {
        this.countries = countries;
    }

}