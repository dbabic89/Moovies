package com.example.android.moovies.domain.models.tv;

import java.io.Serializable;
import java.util.List;

public class Seasons implements Serializable {

    private List<Season> seasons;

    public Seasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }
}
