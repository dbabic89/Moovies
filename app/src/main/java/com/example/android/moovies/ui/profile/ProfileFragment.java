package com.example.android.moovies.ui.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;

public class ProfileFragment extends Fragment implements ProfileMvpView{

    View mView;
    ProfilePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        mPresenter = new ProfilePresenter();
        mPresenter.attachView(this);
        mPresenter.requestToken();
        Log.i("TAG", "pozovi");
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void displayLogin() {

    }

    @Override
    public void displayWatchlist() {

    }

    @Override
    public void displayRatedMovies() {

    }

    @Override
    public void displayLists() {

    }
}
