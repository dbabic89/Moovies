package com.example.android.moovies.ui.season;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.tv.Episode;
import com.example.android.moovies.utils.StringFormating;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.android.moovies.utils.Constants.URL_POSTER;

class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private List<Episode> episodeList;
    private EpisodeAdapter.RecyclerViewInterface recyclerViewInterface;

    @Inject
    Picasso picasso;
    
    @Inject
    EpisodeAdapter() {
        episodeList = new ArrayList<>();
    }

    interface RecyclerViewInterface {
        void onCardClick(int position);
    }

    void setRecyclerViewInterface(EpisodeAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return getViewHolder(parent, layoutInflater);
    }

    @NonNull
    private EpisodeViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View v1 = inflater.inflate(R.layout.list_item_episode, parent, false);
        return new EpisodeViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, final int position) {
        Episode episode = episodeList.get(position);

        int x = position + 1;
        String releaseDate = " - ";

        try {
            if (!episode.getAirDate().isEmpty())
                releaseDate = StringFormating.dateFormating(episode.getAirDate());
        } catch (NullPointerException e) {
            Log.i("TAG", e.getMessage());
        }

        String titleAndDate = episode.getName() + " (" + releaseDate + ")";
        holder.episodeTitle.setText(titleAndDate);
        holder.episodeDescription.setText(episode.getOverview());
        holder.episodePosition.setText(String.valueOf(x));
        holder.episodeTmdbRating.setText(String.format("%.1f", episode.getVoteAverage()).replace(",", "."));

        if (episode.getStillPath() == null)
            picasso.load(R.drawable.red_circle).into(holder.episodePoster);
        else
            picasso.load(URL_POSTER + episode.getStillPath()).into(holder.episodePoster);
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public void add(Episode episode) {
        episodeList.add(episode);
        notifyItemInserted(episodeList.size() - 1);
    }

    void addAll(List<Episode> episodes) {

        for (Episode episode : episodes) {
            add(episode);
        }
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView episodePoster;
        TextView episodePosition;
        TextView episodeTitle;
        TextView episodeDescription;
        TextView episodeTmdbRating;

        EpisodeViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            episodePoster = (ImageView) v.findViewById(R.id.image_poster);
            episodePosition = (TextView) v.findViewById(R.id.text_episode_position);
            episodeTitle = (TextView) v.findViewById(R.id.text_episode_name);
            episodeDescription = (TextView) v.findViewById(R.id.text_episode_description);
            episodeTmdbRating = (TextView) v.findViewById(R.id.text_tmdb_rating);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCardClick(getAdapterPosition());
        }
    }
}