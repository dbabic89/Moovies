package com.example.android.moovies.ui.home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.ui.celebrities.CelebritiesFragment;
import com.example.android.moovies.ui.home_movie.HomeMovieFragment;
import com.example.android.moovies.ui.progress.ProgressFragment;
import com.example.android.moovies.ui.tv.TvFragment;
import com.example.android.moovies.ui.common.view_pager.CustomViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, mView);

        List<Fragment> fragmentList = Arrays.asList(new HomeMovieFragment(), new TvFragment(), new CelebritiesFragment(),
                new ProgressFragment());

        List<String> stringList = Arrays.asList("Movies", "TV shows", "Celebs", "Progress");

        new CustomViewPager(fragmentList, stringList, getChildFragmentManager(), mViewPager, mTabLayout, 0);

        return mView;
    }

}