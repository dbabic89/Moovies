package com.example.android.moovies.ui.common.view_pager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.celebrity.CelebsCredits;
import com.example.android.moovies.domain.models.celebrity.Posters;
import com.example.android.moovies.domain.models.tv.Season;
import com.example.android.moovies.domain.models.tv.Seasons;
import com.example.android.moovies.ui.common.mtv_grid.MtvGridFragment;
import com.example.android.moovies.ui.common.mtv_list.ListFragment;
import com.example.android.moovies.ui.home.HomeCelebsFragment;
import com.example.android.moovies.ui.home.HomeMtvFragment;
import com.example.android.moovies.ui.home.HomeProgressFragment;
import com.example.android.moovies.ui.season.SeasonFragment;
import com.example.android.moovies.utils.Constants;

import java.util.ArrayList;
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

    String viewPagerFragment;
    Seasons seasons;
    int currentTab, movieId, tvId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_view_pager, container, false);

        ButterKnife.bind(this, mView);

        fragmentList = new ArrayList<>();
        stringList = new ArrayList<>();

        currentTab = getArguments().getInt(Constants.LIST_ID);
        movieId = getArguments().getInt("movie_id");
        tvId = getArguments().getInt("tv_id");
        viewPagerFragment = getArguments().getString("vpf");
        seasons = (Seasons) getArguments().getSerializable("seasons");

        switch (viewPagerFragment) {

            case "homeFragment":
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);

                Fragment homeMovieFragment = new HomeMtvFragment();
                Bundle homeMovieFragmentBundle = new Bundle();
                homeMovieFragmentBundle.putString(Constants.HOME_MOVIE_FRAGMENT, Constants.HOME_MOVIE_FRAGMENT);
                homeMovieFragment.setArguments(homeMovieFragmentBundle);

                Fragment homeTvFragment = new HomeMtvFragment();
                Bundle homeTvFragmentBundle = new Bundle();
                homeTvFragmentBundle.putString(Constants.HOME_TV_FRAGMENT, Constants.HOME_TV_FRAGMENT);
                homeTvFragment.setArguments(homeTvFragmentBundle);

                fragmentList = Arrays.asList(homeMovieFragment, homeTvFragment, new HomeCelebsFragment(),
                        new HomeProgressFragment());
                stringList = Arrays.asList("Movies", "TV shows", "Celebs", "Progress");

                break;

            case "movieFragment":
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);

                List<Integer> movieTabs = Arrays.asList(0, 1, 2, 3);
                List<String> movieTitles = Arrays.asList("Now", "Upcoming", "Popular", "Top rated");
                startListFragments(movieTabs, movieTitles);

                break;

            case "tvFragment":
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);

                List<Integer> tvTabs = Arrays.asList(4, 5, 6, 7);
                List<String> tvTitles = Arrays.asList("Today", "On air", "Popular", "Top rated");
                startListFragments(tvTabs, tvTitles);

                break;

            case "seasonFragment":

                if (seasons.getSeasons().size() > 6) {
                    mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                } else {
                    mTabLayout.setTabMode(TabLayout.MODE_FIXED);
                }

                if (seasons.getSeasons().size() == 1) {

                    Season season = seasons.getSeasons().get(0);

                    Fragment seasonFragment = new SeasonFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("tv_id", tvId);
                    bundle.putSerializable("season", season);
                    seasonFragment.setArguments(bundle);

                    fragmentList.add(0, seasonFragment);
                    stringList.add(0, "S" + season.getSeasonNumber());

                    mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                } else {

                    for (int i = 0; i < seasons.getSeasons().size(); i++) {

                        Season season = seasons.getSeasons().get(i);

                        Fragment seasonFragment = new SeasonFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("tv_id", tvId);
                        bundle.putSerializable("season", season);
                        seasonFragment.setArguments(bundle);

                        fragmentList.add(i, seasonFragment);
                        stringList.add(i, "S" + season.getSeasonNumber());
                    }
                }

                break;

            case "celebsDetailFragment":
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);

                CelebsCredits celebsCredits = (CelebsCredits) getArguments().getSerializable("credits");

                Fragment posterGridFragment1 = new MtvGridFragment();
                Bundle bundle5 = new Bundle();
                Posters posters1 = new Posters(celebsCredits.getMoviePosters().getMtvPosterList());
                bundle5.putSerializable("movies", posters1);
                posterGridFragment1.setArguments(bundle5);

                Fragment posterGridFragment2 = new MtvGridFragment();
                Bundle bundle6 = new Bundle();
                Posters posters2 = new Posters(celebsCredits.getTvPosters().getMtvPosterList());
                bundle6.putSerializable("tvs", posters2);
                posterGridFragment2.setArguments(bundle6);

                fragmentList = Arrays.asList(posterGridFragment1, posterGridFragment2);
                stringList = Arrays.asList("Movies", "TV shows");
                break;

        }

        new CustomViewPager(fragmentList, stringList, getChildFragmentManager(), mViewPager, mTabLayout, currentTab);

        return mView;
    }

    private void startListFragments(List<Integer> list, List<String> titles) {

        Fragment listFragment1 = new ListFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt(Constants.LIST_ID, list.get(0));
        listFragment1.setArguments(bundle1);

        Fragment listFragment2 = new ListFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt(Constants.LIST_ID, list.get(1));
        listFragment2.setArguments(bundle2);

        Fragment listFragment3 = new ListFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt(Constants.LIST_ID, list.get(2));
        listFragment3.setArguments(bundle3);

        Fragment listFragment4 = new ListFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putInt(Constants.LIST_ID, list.get(3));
        listFragment4.setArguments(bundle4);

        fragmentList = Arrays.asList(listFragment1, listFragment2, listFragment3, listFragment4);
        stringList = titles;
    }

}
