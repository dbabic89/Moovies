package com.example.android.moovies.di.component;

import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.di.scope.ActivityScope;
import com.example.android.moovies.ui.celebs_detail.CelebsDetailFragment;
import com.example.android.moovies.ui.movie_detail.MovieDetailFragment;
import com.example.android.moovies.ui.movie_list.HorizontalRecyclerView;
import com.example.android.moovies.ui.movie_list.MovieListFragment;
import com.example.android.moovies.ui.profile.ProfileFragment;
import com.example.android.moovies.ui.search.SearchFragment;

import dagger.Component;

@ActivityScope
@Component( modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface MovieComponent {

    void inject(MovieListFragment userListFragment);

    void inject(HorizontalRecyclerView horizontalRecyclerView);

    void inject(MovieDetailFragment movieDetailFragment);

    void inject(SearchFragment searchFragment);

    void inject(CelebsDetailFragment celebsDetailFragment);

    void inject(ProfileFragment profileFragment);
}