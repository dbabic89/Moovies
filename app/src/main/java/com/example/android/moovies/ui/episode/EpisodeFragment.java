package com.example.android.moovies.ui.episode;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.tv.Episode;
import com.example.android.moovies.domain.models.tv.Episodes;
import com.example.android.moovies.utils.FragmentCommunication;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EpisodeFragment extends Fragment implements EpisodeMvpView{

    @BindView(R.id.view_pager)
    ViewPager viewPagerEpisodes;

    @Inject
    AllEpisodesAdapter allEpisodesAdapter;

    @Inject
    EpisodePresenter episodePresenter;

    View mView;
    private Episodes episodes;
    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_episode, container, false);

        episodes = (Episodes) getArguments().getSerializable("episodes");
        position = getArguments().getInt("position");

        ButterKnife.bind(this, mView);
        createComponent();
        setPresenter();

        FragmentCommunication fragmentCommunication = (FragmentCommunication) getActivity();
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        allEpisodesAdapter.setEpisodes(episodes);
        allEpisodesAdapter.setFragmentCommunication(fragmentCommunication);
        allEpisodesAdapter.setLayoutInflater(layoutInflater);

        viewPagerEpisodes.setAdapter(allEpisodesAdapter);
        viewPagerEpisodes.setCurrentItem(position);

        return mView;
    }

    private void setPresenter() {
        episodePresenter.attachView(this);
        Episode episode = episodes.getEpisodes().get(position);
        List<Integer> list = Arrays.asList(episode.getId(), episode.getSeasonNumber(), episode.getEpisodeNumber());
        episodePresenter.getEpisodeDetails(list);
    }

    private void createComponent() {
        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);
    }

}
