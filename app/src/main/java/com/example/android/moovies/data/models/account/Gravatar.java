package com.example.android.moovies.data.models.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Laptop on 29.08.2017..
 */

public class Gravatar {

    @SerializedName("hash")
    @Expose
    private String hash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}