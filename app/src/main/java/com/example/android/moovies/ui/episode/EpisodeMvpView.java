package com.example.android.moovies.ui.episode;

import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.domain.models.mtv.Videos;
import com.example.android.moovies.ui.base.BaseMvpView;

interface EpisodeMvpView extends BaseMvpView {

    void showPoster(String poster);

    void showSeasonAndEpisodeNum(String num);

    void showEpisodeName(String name);

    void showEpisodeRating(float rating, int count);

    void showEpisodeOverview(String overview);

    void showImages(Images images);

    void showNoImages();

    void showVideos(Videos videos, String title, String overview);

    void showNoVideos();

    void showGuestStars();

    void showRating(int rating);

}
