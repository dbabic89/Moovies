package com.example.android.moovies.domain.models.celebrity;

import com.example.android.moovies.domain.models.mtv.MtvPoster;

import java.io.Serializable;
import java.util.List;

public class Posters implements Serializable {

    List<MtvPoster> mtvPosterList;

    public Posters(List<MtvPoster> mtvPosterList) {
        this.mtvPosterList = mtvPosterList;
    }

    public List<MtvPoster> getMtvPosterList() {
        return mtvPosterList;
    }

    public void setMtvPosterList(List<MtvPoster> mtvPosterList) {
        this.mtvPosterList = mtvPosterList;
    }

}
