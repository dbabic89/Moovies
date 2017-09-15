package com.example.android.moovies.domain.models.celebrity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalIds {

    @SerializedName("freebase_id")
    @Expose
    private Object freebaseId;
    @SerializedName("instagram_id")
    @Expose
    private Object instagramId;
    @SerializedName("tvrage_id")
    @Expose
    private Object tvrageId;
    @SerializedName("twitter_id")
    @Expose
    private Object twitterId;
    @SerializedName("freebase_mid")
    @Expose
    private Object freebaseMid;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("facebook_id")
    @Expose
    private Object facebookId;

    public Object getFreebaseId() {
        return freebaseId;
    }

    public void setFreebaseId(Object freebaseId) {
        this.freebaseId = freebaseId;
    }

    public Object getInstagramId() {
        return instagramId;
    }

    public void setInstagramId(Object instagramId) {
        this.instagramId = instagramId;
    }

    public Object getTvrageId() {
        return tvrageId;
    }

    public void setTvrageId(Object tvrageId) {
        this.tvrageId = tvrageId;
    }

    public Object getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(Object twitterId) {
        this.twitterId = twitterId;
    }

    public Object getFreebaseMid() {
        return freebaseMid;
    }

    public void setFreebaseMid(Object freebaseMid) {
        this.freebaseMid = freebaseMid;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Object getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(Object facebookId) {
        this.facebookId = facebookId;
    }

}