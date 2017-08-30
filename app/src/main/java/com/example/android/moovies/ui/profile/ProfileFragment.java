package com.example.android.moovies.ui.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.moovies.R;
import com.example.android.moovies.data.local.SharedPreferencesManager;

public class ProfileFragment extends Fragment implements ProfileMvpView{

    Button buttonLogin, buttonLogout;
    View mView;
    ProfilePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getActivity());

        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        buttonLogin = (Button) mView.findViewById(R.id.button_login);
        buttonLogout = (Button) mView.findViewById(R.id.button_logout);

        mPresenter = new ProfilePresenter(sharedPreferencesManager);
        mPresenter.attachView(this);
        mPresenter.userLogin();

        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 11) {
            mPresenter.getSessionID(data.getExtras().getString("token"));
        }
    }

    @Override
    public void displayLogin() {

        buttonLogin.setVisibility(View.VISIBLE);
        buttonLogout.setVisibility(View.GONE);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getRequestToken();
            }
        });
    }

    @Override
    public void startWebView(final String token) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("token", token);
        startActivityForResult(intent, 11);
    }

    @Override
    public void displayProfile(String sessionId) {

        buttonLogout.setVisibility(View.VISIBLE);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.userLogout();
            }
        });
        buttonLogin.setVisibility(View.GONE);
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
