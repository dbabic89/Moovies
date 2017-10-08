package com.example.android.moovies.ui.episode;

import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.Credits;
import com.example.android.moovies.domain.models.tv.Episode;
import com.example.android.moovies.domain.models.tv.Episodes;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.StringFormating;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

class AllEpisodesAdapter extends PagerAdapter {

    private Episodes episodes;
    private FragmentCommunication fragmentCommunication;
    private LayoutInflater layoutInflater;

    @Inject
    Picasso picasso;

    private Button button;
    private TextView episodeOverview;
    private TextView episodeVote;
    private TextView episodeName;
    private TextView textSeasonName;
    private ImageView imagePoster;

    @Inject
    AllEpisodesAdapter() {}

    @Override
    public int getCount() {
        return this.episodes.getEpisodes().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View viewLayout = layoutInflater.inflate(R.layout.view_episode, container, false);

        initializeViews(viewLayout);

        final Episode episode = episodes.getEpisodes().get(position);

        picasso.load(Constants.URL_STILL_POSTER + episode.getStillPath()).into(imagePoster);
        textSeasonName.setText("Season " + episode.getSeasonNumber() + "   Episode " + episode.getEpisodeNumber());
        episodeName.setText(episode.getName() + " (" + StringFormating.dateFormating(episode.getAirDate()) + ")");
        episodeOverview.setText(episode.getOverview());
        episodeVote.setText("Rating " + String.format("%.1f", episode.getVoteAverage()).replace(",", ".") + " Vote count: " + episode.getVoteCount());

        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Credits credits = new Credits();
                credits.setCast(episode.getGuestStars());
                fragmentCommunication.startCelebrityList(credits);
            }
        });

        (container).addView(viewLayout);

        return viewLayout;
    }

    void setPoster(String poster) {
        picasso.load(Constants.URL_STILL_POSTER + poster).into(imagePoster);
    }

    private void initializeViews(View viewLayout) {
        imagePoster = (ImageView) viewLayout.findViewById(R.id.image_poster);
        textSeasonName = (TextView) viewLayout.findViewById(R.id.text_season_name);
        episodeName = (TextView) viewLayout.findViewById(R.id.text_episode_name);
        episodeVote = (TextView) viewLayout.findViewById(R.id.text_episode_vote);
        episodeOverview = (TextView) viewLayout.findViewById(R.id.text_episode_overview);
        button = (Button) viewLayout.findViewById(R.id.button_see_more);
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {
        (container).removeView((NestedScrollView) object);

    }

    void setEpisodes (Episodes episodes){
        this.episodes = episodes;
    }

    void setFragmentCommunication (FragmentCommunication fragmentCommunication){
        this.fragmentCommunication = fragmentCommunication;
    }

   void setLayoutInflater (LayoutInflater layoutInflater){
        this.layoutInflater = layoutInflater;
    }

}