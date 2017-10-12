package com.example.android.moovies.domain.models.search;

public class SearchListItem {

    private int id;
    private String title;
    private String poster;
    private String type;

    SearchListItem(int id, String title, String poster, String type) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
