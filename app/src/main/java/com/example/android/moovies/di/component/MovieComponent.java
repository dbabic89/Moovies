package com.example.android.moovies.di.component;

import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.di.scope.ActivityScope;
import com.example.android.moovies.ui.celebs_detail.CelebsDetailFragment;
import com.example.android.moovies.ui.episode.EpisodeFragment;
import com.example.android.moovies.ui.movie_detail.MovieDetailFragment;
import com.example.android.moovies.ui.common.mtv_list.HorizontalRecyclerView;
import com.example.android.moovies.ui.common.mtv_list.ListFragment;
import com.example.android.moovies.ui.profile.ProfileFragment;
import com.example.android.moovies.ui.search.SearchFragment;
import com.example.android.moovies.ui.season.SeasonFragment;
import com.example.android.moovies.ui.tv_detail.TvDetailFragment;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface MovieComponent {

    void inject(ListFragment userListFragment);

    void inject(HorizontalRecyclerView horizontalRecyclerView);

    void inject(MovieDetailFragment movieDetailFragment);

    void inject(TvDetailFragment tvDetailFragment);

    void inject(SearchFragment searchFragment);

    void inject(CelebsDetailFragment celebsDetailFragment);

    void inject(ProfileFragment profileFragment);

    void inject(SeasonFragment seasonFragment);

    void inject(EpisodeFragment episodeFragment);
}