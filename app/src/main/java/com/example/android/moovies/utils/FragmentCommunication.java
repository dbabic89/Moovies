package com.example.android.moovies.utils;

import com.example.android.moovies.domain.models.movie.Reviews;
import com.example.android.moovies.domain.models.mtv.Credits;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.domain.models.tv.Episodes;
import com.example.android.moovies.domain.models.tv.Seasons;

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

    void startImageGallery(Images images);

    void startImageDetail(Images imageList, int position);

    void startSeasonFragment(Seasons seasons, int id);

    void startEpisodes(int tvId, Episodes episodes, int position);

    void startDiscoverFragment();
}
