package com.example.android.moovies.ui.movie_detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.android.moovies.R;
import com.example.android.moovies.ui.base.BaseActivity;
import com.example.android.moovies.ui.common.view_pager.ViewPagerFragment;

public class MovieDetailActivity extends BaseActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Fragment viewPager = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("vpf", "movieDetailFragment");
        viewPager.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, viewPager).commit();
    }
}
