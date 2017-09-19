package com.example.android.moovies.utils;

import com.example.android.moovies.domain.models.mtv.Credits;
import com.example.android.moovies.domain.models.movie.Reviews;

public interface FragmentCommunication {

    void startTabs(int tab, int type);

    void startList(int id, int tab);

    void startMovieDetail(int id);

    void startTvDetail(int id);

    void startReviewList(Reviews reviews);

    void startCollectionList(int id);

    void startSearchDetail(int id);

    void startCelebrityDetail(int id);

    void startCelebrityList(Credits credits);

    void closeSearch();
}
