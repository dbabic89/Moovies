package com.example.android.moovies.domain.models.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountStatesRated {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("favorite")
    @Expose
    private boolean favorite;
    @SerializedName("rated")
    @Expose
    private boolean rated;
    @SerializedName("watchlist")
    @Expose
    private boolean watchlist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

}