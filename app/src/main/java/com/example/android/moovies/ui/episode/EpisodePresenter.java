package com.example.android.moovies.ui.episode;

import android.util.Log;

import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.models.account.EpisodeRating;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.tv.EpisodeDetail;
import com.example.android.moovies.domain.use_case.AddEpisodeRating;
import com.example.android.moovies.domain.use_case.DeleteEpisodeRating;
import com.example.android.moovies.domain.use_case.GetAccountStatesEpisode;
import com.example.android.moovies.domain.use_case.GetEpisodeDetails;
import com.example.android.moovies.ui.base.BasePresenter;
import com.example.android.moovies.utils.StringFormating;

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
    EpisodePresenter() {
    }

    private int rating = 0;

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

    void getEpisodeAccountStates(List<Integer> list){
        getAccountStatesEpisode.execute(new AccountStatesEpisodeObserver(), list);
    }

    void addRating(EpisodeRating episodeRating) {
        addEpisodeRating.execute(new RatingObserver(), episodeRating);
        this.rating = episodeRating.getRated().getValue();
    }

    void deleteRating(EpisodeRating episodeRating) {
        deleteEpisodeRating.execute(new RatingObserver(), episodeRating);
        this.rating = episodeRating.getRated().getValue();
    }

    private class EpisodeDetailsObserver extends DisposableObserver<EpisodeDetail> {
        @Override
        public void onNext(EpisodeDetail value) {
            getMvpView().showPoster(value.getStillPath());
            getMvpView().showSeasonAndEpisodeNum("Season " + value.getSeasonNumber() + "   Episode " + value.getEpisodeNumber());
            getMvpView().showEpisodeName(value.getName() + " (" + StringFormating.dateFormating(value.getAirDate()) + ")");
            getMvpView().showEpisodeOverview(value.getOverview());
            if (value.getImages().getStills().size() != 0)getMvpView().showImages(value.getImages());
            else getMvpView().showNoImages();
            if (value.getVideos().getResults().size() != 0)getMvpView().showVideos(value.getVideos(), value.getName(), value.getOverview());
            if (!value.getGuestStars().isEmpty()) getMvpView().showGuestStars();
            getMvpView().showEpisodeRating(value.getVoteAverage(), value.getVoteCount());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private class AccountStatesEpisodeObserver extends DisposableObserver<AccountStates> {
        @Override
        public void onNext(AccountStates value) {
            getMvpView().showRating(value.getRating());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private class RatingObserver extends DisposableObserver<PostResponse> {
        @Override
        public void onNext(PostResponse value) {
            getMvpView().showRating(rating);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
