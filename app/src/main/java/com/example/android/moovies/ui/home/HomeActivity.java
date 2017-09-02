package com.example.android.moovies.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.moovies.R;
import com.example.android.moovies.data.models.movie.Reviews;
import com.example.android.moovies.ui.base.BaseActivity;
import com.example.android.moovies.ui.common.view_pager.ViewPagerFragment;
import com.example.android.moovies.ui.movie_detail.MovieDetailFragment;
import com.example.android.moovies.ui.movie_list.MovieListFragment;
import com.example.android.moovies.ui.profile.ProfileFragment;
import com.example.android.moovies.ui.review_list.ReviewListFragment;
import com.example.android.moovies.utils.FragmentCommunication;

public class HomeActivity extends BaseActivity implements FragmentCommunication {

    Fragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setElevation(0);

        Fragment viewPager = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("vpf", "homeFragment");
        viewPager.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, viewPager).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_profile) {

            fragment = new ProfileFragment();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).addToBackStack("tag").commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startMovieTabs(int tab) {

        Fragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("vpf", "movieFragment");
        bundle.putInt("currentTab", tab);
        fragment.setArguments(bundle);

        fragmentManager.beginTransaction().add(R.id.content_main, fragment).addToBackStack("tag").commit();
    }

    @Override
    public void startMovieDetail(int id) {

        Fragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("vpf", "movieDetailFragment");
        bundle.putInt("movie_id", id);
        movieDetailFragment.setArguments(bundle);

        fragmentManager.beginTransaction().add(R.id.content_main, movieDetailFragment).addToBackStack("tag").commit();
    }

    @Override
    public void startReviewList(Reviews reviews) {

        Fragment reviewListFragment = new ReviewListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("reviews", reviews);
        reviewListFragment.setArguments(bundle);

        fragmentManager.beginTransaction().add(R.id.content_main, reviewListFragment).addToBackStack("tag").commit();
    }

    @Override
    public void startCollectionList(int id) {

        Fragment movieListFragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("collection_id", id);
        bundle.putInt("tab", 5);
        movieListFragment.setArguments(bundle);

        fragmentManager.beginTransaction().add(R.id.content_main, movieListFragment).addToBackStack("tag").commit();
    }

}
