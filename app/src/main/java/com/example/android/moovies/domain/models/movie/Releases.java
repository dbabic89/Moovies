package com.example.android.moovies.domain.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Releases {

    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}