package com.example.android.moovies.domain.models.celebrity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("iso_639_1")
    @Expose
    private Object iso6391;
    @SerializedName("aspect_ratio")
    @Expose
    private float aspectRatio;
    @SerializedName("vote_count")
    @Expose
    private int voteCount;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("vote_average")
    @Expose
    private int voteAverage;
    @SerializedName("file_path")
    @Expose
    private String filePath;
    @SerializedName("width")
    @Expose
    private int width;

    public Object getIso6391() {
        return iso6391;
    }

    public void setIso6391(Object iso6391) {
        this.iso6391 = iso6391;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}