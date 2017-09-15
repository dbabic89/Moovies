package com.example.android.moovies.ui.celebs_detail;

import com.example.android.moovies.domain.models.celebrity.MovieCredits;
import com.example.android.moovies.domain.models.celebrity.TvCredits;
import com.example.android.moovies.ui.base.BaseMvpView;

public interface CelebsDetailMvpView extends BaseMvpView{

    void showPoster(String poster);

    void showName(String name);

    void showBirthday(String birthday);

    void showBirthplace(String birthday);

    void showDeathday(String birthday);

    void showBiography(String birthday);

    void showCredits(MovieCredits movieCredits, TvCredits tvCredits);
}
