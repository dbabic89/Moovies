package com.example.android.moovies.ui.profile;

import android.util.Log;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.domain.models.account.Account;
import com.example.android.moovies.domain.models.authentication.Session;
import com.example.android.moovies.domain.models.authentication.Token;
import com.example.android.moovies.domain.use_case.GetMovieList;
import com.example.android.moovies.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ProfilePresenter extends BasePresenter<ProfileMvpView> {

    @Inject
    TmdbInterface mTmdbInterface;
    @Inject
    SharedPreferencesManager sharedPreferencesManager;
    @Inject
    GetMovieList getMovieList;

    @Inject
    ProfilePresenter(SharedPreferencesManager sharedPreferencesManager, TmdbInterface tmdbInterface) {
        this.sharedPreferencesManager = sharedPreferencesManager;
        this.mTmdbInterface = tmdbInterface;
    }

    @Override
    public void attachView(ProfileMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    void userLogin() {

        if (!sharedPreferencesManager.isLoggedIn()) {
            getMvpView().displayLogin();
        } else {
            getMvpView().displayProfile(sharedPreferencesManager.getSessionId());
        }

    }

    void userLogout() {
        sharedPreferencesManager.logout();
        getMvpView().displayLogin();
    }

    void getRequestToken() {
        Call<Token> call = mTmdbInterface.getToken(BuildConfig.TMDB_APIKEY);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                getMvpView().startWebView(response.body().getRequestToken());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }

    void getSessionID(String token) {
        Call<Session> call = mTmdbInterface.getSessionId(BuildConfig.TMDB_APIKEY, token);
        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                getMvpView().displayProfile(response.body().getSessionId());
                sharedPreferencesManager.createLoggingSession(response.body().getSessionId());
                getAccountId();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {

            }
        });
    }

    void getAccountId() {
        Observable<Account> observable = mTmdbInterface.getAccountDetail(BuildConfig.TMDB_APIKEY, sharedPreferencesManager.getSessionId());
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Account>() {
                    @Override
                    public void onNext(Account value) {
                        if (value != null) {
                            sharedPreferencesManager.setAccountId(value.getId());
                            Log.i("TAG", value.getAvatar().getGravatar().getHash());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
