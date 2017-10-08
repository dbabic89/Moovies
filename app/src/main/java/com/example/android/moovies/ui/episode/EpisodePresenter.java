package com.example.android.moovies.ui.episode;

import android.util.Log;

import com.example.android.moovies.domain.models.tv.EpisodeDetail;
import com.example.android.moovies.domain.use_case.AddEpisodeRating;
import com.example.android.moovies.domain.use_case.DeleteEpisodeRating;
import com.example.android.moovies.domain.use_case.GetAccountStatesEpisode;
import com.example.android.moovies.domain.use_case.GetEpisodeDetails;
import com.example.android.moovies.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class EpisodePresenter extends BasePresenter<EpisodeMvpView>{

    @Inject
    AddEpisodeRating addEpisodeRating;

    @Inject
    DeleteEpisodeRating deleteEpisodeRating;

    @Inject
    GetEpisodeDetails getEpisodeDetails;

    @Inject
    GetAccountStatesEpisode getAccountStatesEpisode;

    @Inject
    public EpisodePresenter() {
    }

    @Override
    public void attachView(EpisodeMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        getEpisodeDetails.dispose();
    }

    void getEpisodeDetails(List<Integer> list){
        getEpisodeDetails.execute(new EpisodeDetailsObserver(), list);
    }

    void getEpisodeAccountStates(){

    }

    private class EpisodeDetailsObserver extends DisposableObserver<EpisodeDetail> {
        @Override
        public void onNext(EpisodeDetail value) {
            Log.i("TAG", value.getName());
        }

        @Override
        public void onError(Throwable e) {
            Log.i("TAG", e.getMessage());

        }

        @Override
        public void onComplete() {

        }
    }
}
