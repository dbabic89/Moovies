package com.example.android.moovies.utils;

import com.example.android.moovies.data.models.movie.Reviews;

public interface FragmentCommunication {

    void startMovieTabs(int tab);

    void startMovieDetail(int id);

    void startReviewList(Reviews reviews);

    void startCollectionList(int id);
}
