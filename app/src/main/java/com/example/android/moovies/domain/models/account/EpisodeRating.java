package com.example.android.moovies.domain.models.account;

public class EpisodeRating {

    private int id;
    private int s_num;
    private int e_num;
    private Rated rated;

    public EpisodeRating(int id, int s_num, int e_num, Rated rated) {
        this.id = id;
        this.s_num = s_num;
        this.e_num = e_num;
        this.rated = rated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getS_num() {
        return s_num;
    }

    public void setS_num(int s_num) {
        this.s_num = s_num;
    }

    public int getE_num() {
        return e_num;
    }

    public void setE_num(int e_num) {
        this.e_num = e_num;
    }

    public Rated getRated() {
        return rated;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }
}
