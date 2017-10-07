package com.example.android.moovies.ui.season;

import com.example.android.moovies.domain.models.tv.SeasonDetail;
import com.example.android.moovies.domain.use_case.GetSeason;
import com.example.android.moovies.ui.base.BasePresenter;
import com.example.android.moovies.utils.StringFormating;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

class SeasonPresenter extends BasePresenter<SeasonMvpView> {

    @Inject
    GetSeason getSeason;

    @Inject
    SeasonPresenter(GetSeason getSeason) {
        this.getSeason = getSeason;
    }

    @Override
    public void attachView(SeasonMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        getSeason.dispose();
    }

    @Override
    public SeasonMvpView getMvpView() {
        return super.getMvpView();
    }

    void getSeasonDetails(List<Integer> list) {
        getSeason.execute(new GetSeasonObserver(), list);
    }

    private class GetSeasonObserver extends DisposableObserver<SeasonDetail> {
        @Override
        public void onNext(SeasonDetail value) {

            getMvpView().showSeasonName(value.getName());
            getMvpView().showAirDate("Release date\n" + StringFormating.dateFormating(value.getAirDate()));
            getMvpView().showEpisodeNum(value.getEpisodes().size() + " episodes");
            getMvpView().showOverview(value.getOverview());
            getMvpView().showEpisodes(value.getEpisodes());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
