package com.example.android.moovies.data.models.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountStatesRating {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("favorite")
    @Expose
    private boolean favorite;
    @SerializedName("rated")
    @Expose
    private Rated rated;
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

    public Rated getRated() {
        return rated;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

}