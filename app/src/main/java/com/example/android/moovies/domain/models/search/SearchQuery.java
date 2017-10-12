package com.example.android.moovies.domain.models.search;

public class SearchQuery {

    private String query;
    private int page;

    public SearchQuery(String query, int page) {
        this.query = query;
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
