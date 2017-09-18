package com.example.android.moovies.ui.tv_detail;

import android.util.Log;

import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.domain.models.tv.TvDetail;
import com.example.android.moovies.domain.use_case.GetAccountTvStates;
import com.example.android.moovies.domain.use_case.GetTvDetails;
import com.example.android.moovies.ui.base.BasePresenter;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.StringFormating;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class TvDetailPresenter extends BasePresenter<TvDetailMvpView> {

    @Inject
    GetTvDetails getTvDetail;

    @Inject
    GetAccountTvStates getAccountTvStates;

    @Inject
    SharedPreferencesManager mSharedPreferencesManager;

    @Inject
    TvDetailPresenter(SharedPreferencesManager mSharedPreferencesManager) {
        this.mSharedPreferencesManager = mSharedPreferencesManager;
    }

    @Override
    public void attachView(TvDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    void getTvDetails(int tvId) {
        getTvDetail.execute(new TvDetailObserver(), tvId);
    }

    private class TvDetailObserver extends DisposableObserver<TvDetail> {
        @Override
        public void onNext(TvDetail value) {

            getMvpView().showDetails(String.valueOf(value.getVoteAverage()), value.getName(), String.valueOf(value.getVoteCount()));
            getMvpView().showPoster(Constants.URL_POSTER + value.getPosterPath());
            getMvpView().showBackdrop(Constants.URL_BACKDROP + value.getBackdropPath());
            getMvpView().showGenres(value.getGenres());
            getMvpView().showContentRating(value.getContentRatings().getResults());
            getMvpView().showReleaseDate(StringFormating.dateFormating(value.getFirstAirDate()));
            getMvpView().showDuration(value.getEpisodeRunTime().get(0) + " min");
            getMvpView().showOverview(value.getOverview());
            getMvpView().showReleaseDate(StringFormating.dateFormating(value.getFirstAirDate()));
            getMvpView().showImages(value.getImages().getBackdrops());
            if (value.getVideos().getResults().size() != 0) getMvpView().showVideos(value.getVideos(), value.getName(), value.getOverview());
            else getMvpView().showNoVideos();
            getMvpView().showKeywords(value.getKeywords().getResults());
            getMvpView().showOriginalTitle(value.getOriginalName());
            getMvpView().showNetwork(StringFormating.getNetworks(value.getNetworks()));
            getMvpView().showStatus(value.getStatus() + " (" + value.isInProduction()+ ")");
            getMvpView().showProductionCompanies(StringFormating.companyFormating(value.getProductionCompanies()));
            getMvpView().showProductionCountries(value.getOriginCountry().toString());
            getMvpView().showSpokenLanguage(value.getOriginalLanguage());
            getMvpView().showSimilarTvs(value.getId());
            if (value.getCredits() != null){
                if (value.getCreatedBy() != null) getMvpView().showCreatedBy(value.getCreatedBy());
                if (value.getCredits().getCast() != null)getMvpView().showCast(value.getCredits().getCast());
            }

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
