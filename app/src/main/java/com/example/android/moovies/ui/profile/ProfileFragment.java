package com.example.android.moovies.ui.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.ui.common.mtv_list.ListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements ProfileMvpView, View.OnClickListener {

    @BindView(R.id.button_login)
    Button buttonLogin;

    @BindView(R.id.button_logout)
    Button buttonLogout;

    @BindView(R.id.button_rated_movies)
    Button buttonRatedMovies;

    @BindView(R.id.button_movie_watchlist)
    Button buttonMoviesWatclist;

    @BindView(R.id.button_rated_tvs)
    Button buttonRatedTvs;

    @BindView(R.id.button_tv_watchlist)
    Button buttonTvsWatclist;

    @BindView(R.id.button_lists)
    Button buttonList;

    @Inject
    ProfilePresenter mPresenter;

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        createComponent();

        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, mView);

        setPresenter();

        return mView;
    }

    private void createComponent() {
        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == 11) {
                mPresenter.getSessionID(data.getExtras().getString("token"));
            }
        }
    }

    @Override
    public void displayLogin() {

        buttonLogin.setVisibility(View.VISIBLE);
        buttonLogout.setVisibility(View.GONE);
        buttonRatedMovies.setVisibility(View.GONE);
        buttonMoviesWatclist.setVisibility(View.GONE);
        buttonRatedTvs.setVisibility(View.GONE);
        buttonTvsWatclist.setVisibility(View.GONE);
        buttonList.setVisibility(View.GONE);

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


        buttonLogin.setVisibility(View.GONE);
        buttonLogout.setVisibility(View.VISIBLE);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.userLogout();
                buttonLogout.setVisibility(View.GONE);
            }
        });

        buttonRatedMovies.setVisibility(View.VISIBLE);
        buttonMoviesWatclist.setVisibility(View.VISIBLE);
        buttonRatedTvs.setVisibility(View.VISIBLE);
        buttonTvsWatclist.setVisibility(View.VISIBLE);
        buttonList.setVisibility(View.VISIBLE);

        buttonRatedMovies.setOnClickListener(this);
        buttonMoviesWatclist.setOnClickListener(this);
        buttonRatedTvs.setOnClickListener(this);
        buttonTvsWatclist.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        Fragment fragment = null;

        if (id == R.id.button_rated_movies) {
            fragment = ListFragment.newInstance(0, 0, 11);
        } else if (id == R.id.button_movie_watchlist) {
            fragment = ListFragment.newInstance(0, 0, 12);
        } else if (id == R.id.button_tv_watchlist) {
            fragment = ListFragment.newInstance(0, 0, 13);
        } else if (id == R.id.button_rated_tvs) {
            fragment = ListFragment.newInstance(0, 0, 14);
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).addToBackStack("Tag").commit();
    }

    private void setPresenter() {
        mPresenter.attachView(this);
        mPresenter.userLogin();
    }
}
