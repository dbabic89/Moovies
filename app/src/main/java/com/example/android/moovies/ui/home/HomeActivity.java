package com.example.android.moovies.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.moovies.R;
import com.example.android.moovies.ui.base.BaseActivity;
import com.example.android.moovies.ui.movie_view_pager.MovieViewPagerFragment;
import com.example.android.moovies.ui.profile.ProfileFragment;
import com.example.android.moovies.utils.FragmentCommunication;

public class HomeActivity extends BaseActivity implements FragmentCommunication{

    Fragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setElevation(0);

        Fragment homeFragment = new HomeFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, homeFragment).commit();
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
        } else if (id == R.id.action_profile){

            if (fragment == null){
                fragment = new ProfileFragment();
                fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
                return true;
            } else {
                Log.i("TAG", "nece vise");
                return false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startMovieTabs() {

        Fragment movieViewPagerFragment = new MovieViewPagerFragment();
        fragmentManager.beginTransaction().add(R.id.content_main, movieViewPagerFragment).addToBackStack("tag").commit();
    }

}
