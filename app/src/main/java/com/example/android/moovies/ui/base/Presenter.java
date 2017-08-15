package com.example.android.moovies.ui.base;

/**
 * Created by Laptop on 03.08.2017..
 */
public interface Presenter<V extends BaseMvpView> {

    void attachView(V mvpView);

    void detachView();
}