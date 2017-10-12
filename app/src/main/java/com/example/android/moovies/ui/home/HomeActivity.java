package com.example.android.moovies.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.moovies.R;
import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.domain.models.movie.Reviews;
import com.example.android.moovies.domain.models.mtv.Credits;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.domain.models.tv.Episodes;
import com.example.android.moovies.domain.models.tv.Seasons;
import com.example.android.moovies.ui.base.BaseActivity;
import com.example.android.moovies.ui.celebs_detail.CelebsDetailFragment;
import com.example.android.moovies.ui.celebs_list.CelebsListFragment;
import com.example.android.moovies.ui.common.gallery_images.GalleryDetailFragment;
import com.example.android.moovies.ui.common.gallery_images.GalleryGridFragment;
import com.example.android.moovies.ui.common.mtv_list.ListFragment;
import com.example.android.moovies.ui.common.view_pager.ViewPagerFragment;
import com.example.android.moovies.ui.discover.DiscoverFragment;
import com.example.android.moovies.ui.movie_detail.MovieDetailFragment;
import com.example.android.moovies.ui.profile.ProfileFragment;
import com.example.android.moovies.ui.review_list.ReviewListFragment;
import com.example.android.moovies.ui.search.SearchFragment;
import com.example.android.moovies.ui.tv_detail.TvDetailFragment;
import com.example.android.moovies.utils.FragmentCommunication;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements FragmentCommunication {

    @Inject
    TmdbInterface tmdbInteface;

    Fragment fragment;
    MenuItem search;
    SearchView searchView;
    SearchFragment searchFragment;
    FragmentManager fragmentManager;

    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setElevation(0);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, ViewPagerFragment.newInstance("homeFragment", 0, 0, 0, null, null)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        search = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() == 1 && x == 0) {
                    searchFragment = new SearchFragment();
                    fragmentManager.beginTransaction().add(R.id.content_main, searchFragment).commit();
                    searchFragment.showMoviesEmpty();
                    x++;
                } else if (newText.isEmpty()) {
                    if (searchFragment != null) {
                        fragmentManager.beginTransaction().remove(searchFragment).commit();
                        x = 0;
                    }
                } else {
                    searchFragment.searchMovies(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_profile) {
            search.collapseActionView();
            fragment = new ProfileFragment();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).addToBackStack("tag").commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        search.collapseActionView();
        super.onBackPressed();
    }

    @Override
    public void closeSearch() {
        search.collapseActionView();
    }

    @Override
    public void startTabs(int tab, int type) {

        if (type == 1) {
            startFragment(ViewPagerFragment.newInstance("movieFragment", 0, tab, 0, null, null));

        } else {
            startFragment(ViewPagerFragment.newInstance("tvFragment", 0, tab, 0, null, null));
        }

    }

    @Override
    public void startList(int id, int tab) {
        startFragment(ListFragment.newInstance(id, 0, tab));
    }

    @Override
    public void startMovieDetail(int id) {
        startFragment(MovieDetailFragment.newInstance(id));
    }

    @Override
    public void startTvDetail(int id) {
        startFragment(TvDetailFragment.newInstance(id));
    }

    @Override
    public void startReviewList(Reviews reviews) {
        startFragment(ReviewListFragment.newInstance(reviews));
    }

    @Override
    public void startCollectionList(int id) {
        startFragment(ListFragment.newInstance(0, id, 10));
    }

    @Override
    public void startSearchDetail(int id) {
        fragmentManager.popBackStack();
        startFragment(MovieDetailFragment.newInstance(id));
    }

    @Override
    public void startCelebrityDetail(int id) {
        startFragment(CelebsDetailFragment.newInstance(id));
    }

    @Override
    public void startCelebrityList(Credits credits) {
        startFragment(CelebsListFragment.newInstance(credits));
    }

    @Override
    public void startImageGallery(Images images) {
        startFragment(GalleryGridFragment.newInstance(images));
    }

    @Override
    public void startImageDetail(Images images, int position) {
        startFragment(GalleryDetailFragment.newInstance(images, position));
    }

    @Override
    public void startSeasonFragment(Seasons seasons, int id) {
        startFragment(ViewPagerFragment.newInstance("seasonFragment", id, 0, 0, null, seasons));
    }

    @Override
    public void startEpisodes(int id, Episodes episodes, int position) {
        startFragment(ViewPagerFragment.newInstance("episodeFragment", id, 0, position, episodes, null));
    }

    @Override
    public void startDiscoverFragment() {
        fragmentManager.beginTransaction().add(R.id.content_main, new DiscoverFragment()).addToBackStack("tag").commit();
    }

    private void startFragment(Fragment fragment) {
        fragmentManager.beginTransaction().add(R.id.content_main, fragment).addToBackStack("tag").commit();
    }
}