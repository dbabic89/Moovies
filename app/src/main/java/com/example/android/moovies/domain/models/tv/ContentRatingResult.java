package com.example.android.moovies.domain.models.tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentRatingResult {

    @SerializedName("iso_3166_1")
    @Expose
    private String iso31661;
    @SerializedName("rating")
    @Expose
    private String rating;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}