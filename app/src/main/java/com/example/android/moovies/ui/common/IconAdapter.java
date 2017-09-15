package com.example.android.moovies.ui.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.MovieViewHolder> {

    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w185";
    private List<MovieListResult> movies;
    private IconAdapter.RecyclerViewInterface recyclerViewInterface;

    @Inject Picasso picasso;

    @Inject
    IconAdapter() {
        movies = new ArrayList<>();
    }

    public void setRecyclerViewInterface(IconAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public IconAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_movie, parent, false);
        return new IconAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IconAdapter.MovieViewHolder holder, int position) {

        MovieListResult movie = movies.get(position);

        if (movie.getPosterPath() == null) picasso.load(R.drawable.red_circle).into(holder.imageView);
        else picasso.load(BASE_URL_IMG + movie.getPosterPath()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public MovieListResult getItem(int position) {
        return movies.get(position);
    }

    public interface RecyclerViewInterface {

        void onCardClick(int position);

    }
    public void add(MovieListResult movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }

    public void addAll(List<MovieListResult> movies) {
        for (MovieListResult movie : movies) {
            add(movie);
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;

        MovieViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            imageView = (ImageView) v.findViewById(R.id.image_poster);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCardClick(getAdapterPosition());
        }
    }
}