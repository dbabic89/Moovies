package com.example.android.moovies.ui.common.view_pager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.tv.Episode;
import com.example.android.moovies.domain.models.tv.Episodes;
import com.example.android.moovies.domain.models.tv.Season;
import com.example.android.moovies.domain.models.tv.Seasons;
import com.example.android.moovies.ui.common.mtv_list.ListFragment;
import com.example.android.moovies.ui.discover.DiscoverFragment;
import com.example.android.moovies.ui.episode.EpisodeFragment;
import com.example.android.moovies.ui.home.HomeMtvFragment;
import com.example.android.moovies.ui.season.SeasonFragment;
import com.example.android.moovies.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.moovies.utils.Constants.LIST_ID;

public class ViewPagerFragment extends Fragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    View mView;
    List<Fragment> fragmentList;
    List<String> stringList;

    String viewPagerFragment;
    int currentTab, position, tvId = 0;
    Episodes episodes;
    Seasons seasons;

    public static ViewPagerFragment newInstance(String vpf, int tvId, int tab, int position, Episodes episodes, Seasons seasons) {

        Bundle bundle = new Bundle();
        bundle.putString("vpf", vpf);
        bundle.putInt("tv_id", tvId);
        bundle.putInt(LIST_ID, tab);
        bundle.putInt("position", position);
        bundle.putSerializable("episodes", episodes);
        bundle.putSerializable("seasons", seasons);

        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            viewPagerFragment = bundle.getString("vpf");
            tvId = getArguments().getInt("tv_id");
            currentTab = getArguments().getInt(LIST_ID);
            position = getArguments().getInt("position");
            episodes = (Episodes) getArguments().getSerializable("episodes");
            seasons = (Seasons) getArguments().getSerializable("seasons");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_view_pager, container, false);
        ButterKnife.bind(this, mView);

        readBundle(getArguments());

        fragmentList = new ArrayList<>();
        stringList = new ArrayList<>();

        switch (viewPagerFragment) {

            case "homeFragment":
                setFragmentsForHome();
                break;

            case "movieFragment":
                setMovieTabs();
                break;

            case "tvFragment":
                setTvTabs();
                break;

            case "seasonFragment":
                setSeasonTabs();
                break;

            case "episodeFragment":
                setEpisodeTabs();
                break;
        }

        new CustomViewPager(fragmentList, stringList, getChildFragmentManager(), mViewPager, mTabLayout, currentTab);

        return mView;
    }

    private void setEpisodeTabs() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        currentTab = position * (-1);

        for (int i = 0; i < episodes.getEpisodes().size(); i++) {
            Episode episode = episodes.getEpisodes().get(i);

            fragmentList.add(i, EpisodeFragment.newInstance(tvId, episode));
            stringList.add(i, "E" + episode.getEpisodeNumber());
        }

    }

    private void setSeasonTabs() {

        if (seasons.getSeasons().size() > 6) {
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        }

        if (seasons.getSeasons().size() == 1) {
            Season season = seasons.getSeasons().get(0);

            fragmentList.add(0, SeasonFragment.newInstance(season, tvId));
            stringList.add(0, "S" + season.getSeasonNumber());

            mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        } else {

            for (int i = 0; i < seasons.getSeasons().size(); i++) {
                Season season = seasons.getSeasons().get(i);

                fragmentList.add(i, SeasonFragment.newInstance(season, tvId));
                stringList.add(i, "S" + season.getSeasonNumber());
            }
        }
    }

    private void setMovieTabs() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        List<Integer> movieTabs = Arrays.asList(0, 1, 2, 3);
        List<String> movieTitles = Arrays.asList("Now", "Upcoming", "Popular", "Top rated");
        startListFragments(movieTabs, movieTitles);
    }

    private void setTvTabs() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        List<Integer> tvTabs = Arrays.asList(4, 5, 6, 7);
        List<String> tvTitles = Arrays.asList("Today", "On air", "Popular", "Top rated");
        startListFragments(tvTabs, tvTitles);
    }

    private void setFragmentsForHome() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        Fragment homeMovieFragment = new HomeMtvFragment();
        Bundle homeMovieFragmentBundle = new Bundle();
        homeMovieFragmentBundle.putString(Constants.HOME_MOVIE_FRAGMENT, Constants.HOME_MOVIE_FRAGMENT);
        homeMovieFragment.setArguments(homeMovieFragmentBundle);

        Fragment homeTvFragment = new HomeMtvFragment();
        Bundle homeTvFragmentBundle = new Bundle();
        homeTvFragmentBundle.putString(Constants.HOME_TV_FRAGMENT, Constants.HOME_TV_FRAGMENT);
        homeTvFragment.setArguments(homeTvFragmentBundle);

        fragmentList = Arrays.asList(homeMovieFragment, homeTvFragment, new DiscoverFragment());
        stringList = Arrays.asList("Movies", "TV shows", "Discover");
    }

    private void startListFragments(List<Integer> list, List<String> titles) {

        for (int i = 0; i < list.size(); i++){
            fragmentList.add(ListFragment.newInstance(0, 0, list.get(i)));
            stringList.add(titles.get(i));
        }
    }

}