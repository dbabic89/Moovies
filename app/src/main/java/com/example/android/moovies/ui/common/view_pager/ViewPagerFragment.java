package com.example.android.moovies.ui.common.view_pager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.ui.home.HomeCelebsFragment;
import com.example.android.moovies.ui.home.HomeMovieFragment;
import com.example.android.moovies.ui.movie_list.MovieListFragment;
import com.example.android.moovies.ui.progress.ProgressFragment;
import com.example.android.moovies.ui.tv.TvFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerFragment extends Fragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    View mView;
    List<Fragment> fragmentList;
    List<String> stringList;

    String vpf;
    int x, currentTab, movieId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_view_pager, container, false);

        ButterKnife.bind(this, mView);

        currentTab = getArguments().getInt("currentTab");
        movieId = getArguments().getInt("movie_id");
        vpf = getArguments().getString("vpf");

        switch (vpf) {

            case "homeFragment":
                fragmentList = Arrays.asList(new HomeMovieFragment(), new TvFragment(), new HomeCelebsFragment(),
                        new ProgressFragment());
                stringList = Arrays.asList("Movies", "TV shows", "Celebs", "Progress");

                break;

            case "movieFragment":
                x = getArguments().getInt("curretTab");

                Fragment nowPlayingMovieListFragment = new MovieListFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("tab", 0);
                nowPlayingMovieListFragment.setArguments(bundle1);

                Fragment soonPlayingMovieListFragment = new MovieListFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("tab", 1);
                soonPlayingMovieListFragment.setArguments(bundle2);

                Fragment popularMovieListFragment = new MovieListFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("tab", 2);
                popularMovieListFragment.setArguments(bundle3);

                Fragment topRatedMovieListFragment = new MovieListFragment();
                Bundle bundle4 = new Bundle();
                bundle4.putInt("tab", 3);
                topRatedMovieListFragment.setArguments(bundle4);

                fragmentList = Arrays.asList(nowPlayingMovieListFragment, soonPlayingMovieListFragment, popularMovieListFragment,
                        topRatedMovieListFragment);
                stringList = Arrays.asList("Now", "Upcoming", "Popular", "Top 200");
                break;

        }

        new CustomViewPager(fragmentList, stringList, getChildFragmentManager(), mViewPager, mTabLayout, currentTab);

        return mView;
    }

}
