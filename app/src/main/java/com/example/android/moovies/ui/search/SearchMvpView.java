package com.example.android.moovies.ui.search;

import com.example.android.moovies.domain.models.search.SearchListItem;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface SearchMvpView extends BaseMvpView {

    void showSearchResults(List<SearchListItem> movies);

    void showMoreMovies(List<SearchListItem> movies);

    void showMoviesEmpty();

    void showError();

}
