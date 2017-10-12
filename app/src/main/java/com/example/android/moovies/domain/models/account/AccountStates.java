package com.example.android.moovies.domain.models.account;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class AccountStates {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("favorite")
    @Expose
    private boolean favorite;
    @SerializedName("rated")
    @Expose
    private Object rated;
    @SerializedName("watchlist")
    @Expose
    private boolean watchlist;

    private int rating;

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

    public Object getRated() {
        return rated;
    }

    public void setRated(Object rated) {
        this.rated = rated;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public static class AccountStatesDeserializer implements JsonDeserializer<AccountStates> {

        @Override
        public AccountStates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            AccountStates accountStates = new Gson().fromJson(json, AccountStates.class);
            JsonObject jsonObject = json.getAsJsonObject();

            JsonElement jsonWatchlist = jsonObject.getAsJsonPrimitive("watchlist");
            if (jsonObject.has("rated")) {
                JsonElement jsonRated = jsonObject.get("rated");
                if (jsonRated.isJsonObject()) {
                    JsonElement jsonRatedValue = jsonRated.getAsJsonObject().get("value");
                    getBoolean(accountStates, jsonWatchlist);
                    accountStates.setRating(jsonRatedValue.getAsInt());
                } else {
                    getBoolean(accountStates, jsonWatchlist);
                    accountStates.setRating(0);
                }
            }
            return accountStates;
        }

        private void getBoolean(AccountStates accountStates, JsonElement jsonWatchlist) {
            try {
                accountStates.setWatchlist(jsonWatchlist.getAsBoolean());
            } catch (NullPointerException e){
                Log.i("TAG", "NullPointerException");
            }
        }
    }
}