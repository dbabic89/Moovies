package com.example.android.moovies.ui.tv_detail;

import android.util.Log;

import com.example.android.moovies.domain.models.account.AccountStates;
import com.example.android.moovies.domain.models.account.MtvRating;
import com.example.android.moovies.domain.models.account.PostResponse;
import com.example.android.moovies.domain.models.account.PostToWatchlist;
import com.example.android.moovies.domain.models.account.Rated;
import com.example.android.moovies.domain.models.tv.TvDetail;
import com.example.android.moovies.domain.observers.AddToWatchlistObserver;
import com.example.android.moovies.domain.use_case.AddToWatchlist;
import com.example.android.moovies.domain.use_case.AddTvRating;
import com.example.android.moovies.domain.use_case.DeleteTvRating;
import com.example.android.moovies.domain.use_case.GetAccountStatesTv;
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
    AddToWatchlist addToWatchlist;

    @Inject
    GetAccountStatesTv getAccountStatesTv;

    @Inject
    AddTvRating addTvRating;

    @Inject
    DeleteTvRating deleteTvRating;

    int tvId, rating = 0;

    @Inject
    TvDetailPresenter() {
    }

    @Override
    public void attachView(TvDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        getTvDetail.dispose();
        getAccountStatesTv.dispose();
        addToWatchlist.dispose();
        addTvRating.dispose();
        deleteTvRating.dispose();
    }

    void getTvDetails(int tvId) {
        getTvDetail.execute(new TvDetailObserver(), tvId);
        getAccountStatesTv.execute(new GetAccountStatesTvObserver(), tvId);
        this.tvId = tvId;
    }

    void addToWatchlist(int tvId, boolean watchlist) {
        addToWatchlist.execute(new AddToWatchlistObserver(), new PostToWatchlist("tv", tvId, watchlist));
    }

    void addRating(int movieId, int rating) {
        addTvRating.execute(new RatingObserver(), new MtvRating(movieId, new Rated(rating)));
        this.rating = rating;
    }

    void deleteRating(int movieId, int rating) {
       deleteTvRating.execute(new RatingObserver(), new MtvRating(movieId, new Rated(rating)));
        this.rating = rating;
    }

    private class TvDetailObserver extends DisposableObserver<TvDetail> {
        @Override
        public void onNext(TvDetail value) {

            getMvpView().showDetails(String.valueOf(value.getVoteAverage()), value.getName(), String.valueOf(value.getVoteCount()), value.getSeasons());
            if (!value.getPosterPath().isEmpty())getMvpView().showPoster(Constants.URL_POSTER + value.getPosterPath());
            else getMvpView().showNoPoster();
            if (!value.getBackdropPath().isEmpty())getMvpView().showBackdrop(Constants.URL_BACKDROP + value.getBackdropPath());
            else getMvpView().showNoBackdrop();
            getMvpView().showGenres(value.getGenres());
            getMvpView().showSeasonAndEpisode("S " + String.valueOf(value.getNumberOfSeasons()), "E " + String.valueOf(value.getNumberOfEpisodes()));
            getMvpView().showReleaseDate(StringFormating.dateFormating(value.getFirstAirDate()));
            getMvpView().showDuration(value.getEpisodeRunTime().get(0) + " min");
            getMvpView().showOverview(value.getOverview());
            getMvpView().showReleaseDate(StringFormating.dateFormating(value.getFirstAirDate()));
            if (value.getImages().getBackdrops().size() != 0)getMvpView().showImages(value.getImages());
            else getMvpView().showNoImages();
            if (value.getVideos().getResults().size() != 0) getMvpView().showVideos(value.getVideos(), value.getName(), value.getOverview());
            else getMvpView().showNoVideos();
            getMvpView().showKeywords(value.getKeywords().getResults());
            getMvpView().showOriginalTitle(" " + value.getOriginalName());
            getMvpView().showNetwork(" " + StringFormating.getNetworks(value.getNetworks()));
            getMvpView().showStatus(" " + value.getStatus());
            getMvpView().showProductionCompanies(" " + StringFormating.companyFormating(value.getProductionCompanies()));
            getMvpView().showProductionCountries(" " + value.getOriginCountry().toString());
            getMvpView().showSpokenLanguage(" " + value.getOriginalLanguage());
            getMvpView().showSimilarTvs(value.getId());
            if (value.getCredits() != null) {
                if (value.getCreatedBy() != null) getMvpView().showCreatedBy(value.getCreatedBy());
                if (value.getCredits().getCast().size() != 0)
                    getMvpView().showCast(value.getCredits().getCast());
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


    private class GetAccountStatesTvObserver extends DisposableObserver<AccountStates> {
        @Override
        public void onNext(AccountStates value) {
            getMvpView().showWatchlist(value.isWatchlist());
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
            Log.i("TAG", e.getMessage());

        }

        @Override
        public void onComplete() {

        }
    }
}
