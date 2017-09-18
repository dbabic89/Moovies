package com.example.android.moovies.domain.models.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostMovieToWatchlist {

    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("media_id")
    @Expose
    private int mediaId;
    @SerializedName("watchlist")
    @Expose
    private boolean watchlist;

    public PostMovieToWatchlist(String mediaType, int mediaId, boolean watchlist) {
        this.mediaType = mediaType;
        this.mediaId = mediaId;
        this.watchlist = watchlist;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

}