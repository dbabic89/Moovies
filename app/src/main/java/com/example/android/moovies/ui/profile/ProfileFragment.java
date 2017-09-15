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
import com.example.android.moovies.ui.movie_list.MovieListFragment;

import javax.inject.Inject;

public class ProfileFragment extends Fragment implements ProfileMvpView, View.OnClickListener {

    Button buttonLogin, buttonLogout, buttonRatedMovies, buttonMoviesWatclist;
    View mView;
    @Inject ProfilePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);

        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeViews();
        setPresenter();

        return mView;
    }

    private void setPresenter() {
        mPresenter.attachView(this);
        mPresenter.userLogin();
    }

    private void initializeViews() {
        buttonLogin = (Button) mView.findViewById(R.id.button_login);
        buttonLogout = (Button) mView.findViewById(R.id.button_logout);
        buttonRatedMovies = (Button) mView.findViewById(R.id.button_rated_movies);
        buttonMoviesWatclist = (Button) mView.findViewById(R.id.button_movie_watchlist);
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

        mPresenter.getAccountId();

        buttonLogin.setVisibility(View.GONE);
        buttonLogout.setVisibility(View.VISIBLE);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.userLogout();
            }
        });
        buttonRatedMovies.setVisibility(View.VISIBLE);
        buttonMoviesWatclist.setVisibility(View.VISIBLE);
        buttonRatedMovies.setOnClickListener(this);
        buttonMoviesWatclist.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        Fragment fragment = new MovieListFragment();

        if (id == R.id.button_rated_movies){

            Bundle bundle = new Bundle();
            bundle.putInt("tab", 6);
            fragment.setArguments(bundle);
        }else if (id == R.id.button_movie_watchlist){

            Bundle bundle = new Bundle();
            bundle.putInt("tab", 7);
            fragment.setArguments(bundle);

        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).addToBackStack("Tag").commit();
    }
}
