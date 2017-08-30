package com.example.android.moovies.data.models.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rated {

    @SerializedName("value")
    @Expose
    private int value;

    public Rated(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}