package com.example.android.moovies.ui.celebs_detail;

import android.util.Log;

import com.example.android.moovies.domain.models.celebrity.Celebrity;
import com.example.android.moovies.domain.use_case.CelebsDetail;
import com.example.android.moovies.ui.base.BasePresenter;
import com.example.android.moovies.utils.StringFormating;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class CelebsDetailPresenter extends BasePresenter<CelebsDetailMvpView> {

    @Inject
    CelebsDetail celebsDetail;

    @Inject
    CelebsDetailPresenter(CelebsDetail celebsDetail) {
        this.celebsDetail = celebsDetail;
    }

    @Override
    public void attachView(CelebsDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        celebsDetail.dispose();
    }

    void getDetails(int id) {
        celebsDetail.execute(new CelebsDetailObserver(), id);
    }

    private class CelebsDetailObserver extends DisposableObserver<Celebrity> {
        @Override
        public void onNext(Celebrity value) {
            if (value != null) {
                getMvpView().showPoster(value.getProfilePath());
                getMvpView().showName(value.getName());
                if (value.getBirthday() != null)
                    getMvpView().showBirthday(StringFormating.dateFormating(value.getBirthday()));
                if (value.getPlaceOfBirth() != null)
                    getMvpView().showBirthplace(value.getPlaceOfBirth());
                if (value.getDeathday() != null)
                    getMvpView().showDeathday(StringFormating.dateFormating(value.getDeathday()));
                if (value.getBiography() != null) getMvpView().showBiography(value.getBiography());
                if (value.getMovieCredits() != null && value.getTvCredits() != null)
                    getMvpView().showCredits(value.getMovieCredits(), value.getTvCredits());
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.i("TAG", "error" + e.getMessage());

        }

        @Override
        public void onComplete() {

        }
    }
}
