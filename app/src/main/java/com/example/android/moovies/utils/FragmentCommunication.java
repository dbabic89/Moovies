package com.example.android.moovies.utils;

import com.example.android.moovies.domain.models.movie.Credits;
import com.example.android.moovies.domain.models.movie.Reviews;

public interface FragmentCommunication {

    void startMovieTabs(int tab);

    void startMovieDetail(int id);

    void startReviewList(Reviews reviews);

    void startCollectionList(int id);

    void startSearchDetail(int id);

    void startCelebrityDetail(int id);

    void startCelebrityList(Credits credits);
}
