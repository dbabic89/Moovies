package com.example.android.moovies.ui.common.mtv_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.MtvListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.android.moovies.utils.Constants.URL_POSTER;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.MovieViewHolder> {

    private List<MtvListItem> listItems;
    private ListAdapter.RecyclerViewInterface recyclerViewInterface;

    @Inject
    Picasso picasso;

    @Inject
    ListAdapter() {
        listItems = new ArrayList<>();
    }

    interface RecyclerViewInterface {
        void onCardClick(int position);
    }

    void setRecyclerViewInterface(ListAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public ListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return getViewHolder(parent, layoutInflater);
    }

    @NonNull
    private ListAdapter.MovieViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View v1 = inflater.inflate(R.layout.list_item_mtv, parent, false);
        return new ListAdapter.MovieViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        MtvListItem listItem = listItems.get(position);

        int x = position + 1;
        String releaseDate = " - ";

        try {
            if (!listItem.getReleaseDate().isEmpty())
                releaseDate = listItem.getReleaseDate().substring(0, 4);
        } catch (NullPointerException e) {
            Log.i("TAG", e.getMessage());
        }

        String titleAndDate = listItem.getTitle() + " (" + releaseDate + ")";
        holder.title.setText(titleAndDate);
        holder.description.setText(listItem.getOverview());
        holder.position.setText(String.valueOf(x));
        if (x > 999) holder.position.setTextSize(10);
        holder.tmdbRating.setText(String.valueOf(listItem.getVoteAverage()));

        if (listItem.getPoster() == null)
            picasso.load(R.drawable.red_circle).into(holder.poster);
        else
            picasso.load(URL_POSTER + listItem.getPoster()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    MtvListItem getItem(int position) {
        return listItems.get(position);
    }

    public void add(MtvListItem item) {
        listItems.add(item);
        notifyItemInserted(listItems.size() - 1);
    }

    void addAll(List<MtvListItem> items) {

        for (MtvListItem item : items) {
            add(item);
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView poster;
        TextView position;
        TextView title;
        TextView description;
        TextView tmdbRating;

        MovieViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            poster = (ImageView) v.findViewById(R.id.image_poster);
            position = (TextView) v.findViewById(R.id.text_movie_position);
            title = (TextView) v.findViewById(R.id.text_movie_title);
            description = (TextView) v.findViewById(R.id.text_movie_description);
            tmdbRating = (TextView) v.findViewById(R.id.text_tmdb_rating);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCardClick(getAdapterPosition());
        }
    }
}