package com.example.android.moovies.data.models.authentication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestTokenBody {

    @SerializedName("redirect_to")
    @Expose
    private String redirectTo;

    public String getRedirectTo() {
        return redirectTo;
    }

    public void setRedirectTo(String redirectTo) {
        this.redirectTo = redirectTo;
    }

}