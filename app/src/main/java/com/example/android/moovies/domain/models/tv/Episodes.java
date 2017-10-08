package com.example.android.moovies.domain.models.tv;

import java.io.Serializable;
import java.util.List;

public class Episodes implements Serializable{

    private List<Episode> episodes;

    public Episodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
