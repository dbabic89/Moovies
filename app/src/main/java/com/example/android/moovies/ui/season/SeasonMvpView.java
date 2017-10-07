package com.example.android.moovies.ui.season;

import com.example.android.moovies.domain.models.tv.Episode;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface SeasonMvpView extends BaseMvpView {

    void showSeasonName(String name);

    void showAirDate(String date);

    void showEpisodeNum(String number);

    void showOverview(String overview);

    void showEpisodes(List<Episode> episodes);



}
