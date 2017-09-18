package com.example.android.moovies.ui.base;

interface Presenter<V extends BaseMvpView> {

    void attachView(V mvpView);

    void detachView();

}