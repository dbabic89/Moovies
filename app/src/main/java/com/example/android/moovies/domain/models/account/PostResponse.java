package com.example.android.moovies.domain.models.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostResponse {

    @SerializedName("status_code")
    @Expose
    private int statusCode;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}