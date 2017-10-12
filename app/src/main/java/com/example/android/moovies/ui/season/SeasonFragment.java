package com.example.android.moovies.ui.season;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.tv.Episode;
import com.example.android.moovies.domain.models.tv.Episodes;
import com.example.android.moovies.domain.models.tv.Season;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.FragmentCommunication;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeasonFragment extends Fragment implements SeasonMvpView {

    @BindView(R.id.image_poster)
    ImageView imageSeasonPoster;

    @BindView(R.id.text_name)
    TextView textSeasonName;

    @BindView(R.id.text_date)
    TextView textSeasonDate;

    @BindView(R.id.text_episode_num)
    TextView textEpisodeNum;

    @BindView(R.id.expand_text_view)
    ExpandableTextView expTvBiography;

    @BindView(R.id.recycler_view_episodes)
    RecyclerView recyclerViewEpisodes;

    @Inject
    Picasso picasso;

    @Inject
    EpisodeAdapter episodeAdapter;

    @Inject
    SeasonPresenter seasonPresenter;

    View mView;
    FragmentCommunication fragmentCommunication;
    private Season season;
    private int tvId;


    public static SeasonFragment newInstance(Season season, int id) {

        Bundle bundle = new Bundle();
        bundle.putInt("tv_id", id);
        bundle.putSerializable("season", season);

        SeasonFragment fragment = new SeasonFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            season = (Season) getArguments().getSerializable("season");
            tvId = getArguments().getInt("tv_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_season, container, false);
        ButterKnife.bind(this, mView);
        readBundle(getArguments());

        createComponent();
        setPresenter(Arrays.asList(tvId, season.getSeasonNumber()));

        fragmentCommunication = (FragmentCommunication) getActivity();

        picasso.load(Constants.URL_POSTER + season.getPosterPath()).into(imageSeasonPoster);

        return mView;
    }

    private void setPresenter(List<Integer> seasonInfo) {
        seasonPresenter.attachView(this);
        seasonPresenter.getSeasonDetails(seasonInfo);
    }

    private void createComponent() {
        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);
    }

    @Override
    public void showSeasonName(String name) {
        textSeasonName.setText(name);
    }

    @Override
    public void showAirDate(String date) {
        textSeasonDate.setText(date);
    }

    @Override
    public void showEpisodeNum(String number) {
        textEpisodeNum.setText(number);
    }

    @Override
    public void showOverview(String overview) {
        expTvBiography.setText(overview);
    }

    @Override
    public void showEpisodes(final List<Episode> episodes) {

        episodeAdapter.addAll(episodes);
        recyclerViewEpisodes.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewEpisodes.setAdapter(episodeAdapter);
        recyclerViewEpisodes.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));
        recyclerViewEpisodes.setNestedScrollingEnabled(false);

        episodeAdapter.setRecyclerViewInterface(new EpisodeAdapter.RecyclerViewInterface() {
            @Override
            public void onClick(int position) {
                fragmentCommunication.startEpisodes(tvId, new Episodes(episodes), position);
            }
        });
    }
}
