package com.example.android.moovies.ui.movie_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.android.moovies.utils.Constants.URL_POSTER;

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<MovieListResult> movies;
    private MovieListAdapter.RecyclerViewInterface recyclerViewInterface;
    private Context context;

    @Inject Picasso picasso;
    @Inject
    MovieListAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    interface RecyclerViewInterface {
        void onCardClick(int position);
    }

    void setRecyclerViewInterface(MovieListAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return getViewHolder(parent, layoutInflater);
    }

    @NonNull
    private MovieListAdapter.MovieViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View v1 = inflater.inflate(R.layout.list_item_movie, parent, false);
        return new MovieListAdapter.MovieViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        MovieListResult movie = movies.get(position);

        int x = position + 1;
        String releaseDate = " - ";

        try {
            if (!movie.getReleaseDate().isEmpty())
                releaseDate = movie.getReleaseDate().substring(0, 4);
        } catch (NullPointerException e) {
            Log.i("TAG", e.getMessage());
        }

        String titleAndDate = movie.getTitle() + " (" + releaseDate + ")";
        holder.movieTitle.setText(titleAndDate);
        holder.movieDescription.setText(movie.getOverview());
        holder.moviePosition.setText(String.valueOf(x));
        holder.movieTmdbRating.setText(String.valueOf(movie.getVoteAverage()));

        if (movie.getPosterPath() == null)
            picasso.load(R.drawable.red_circle).into(holder.moviePoster);
        else
            picasso.load(URL_POSTER + movie.getPosterPath()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    MovieListResult getItem(int position) {
        return movies.get(position);
    }

    public void add(MovieListResult movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }

    void addAll(List<MovieListResult> movies) {

        for (MovieListResult movie : movies) {
            add(movie);
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView moviePoster;
        TextView moviePosition;
        TextView movieTitle;
        TextView movieDescription;
        TextView movieTmdbRating;

        MovieViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            moviePoster = (ImageView) v.findViewById(R.id.image_poster);
            moviePosition = (TextView) v.findViewById(R.id.text_movie_position);
            movieTitle = (TextView) v.findViewById(R.id.text_movie_title);
            movieDescription = (TextView) v.findViewById(R.id.text_movie_description);
            movieTmdbRating = (TextView) v.findViewById(R.id.text_tmdb_rating);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCardClick(getAdapterPosition());
        }
    }
}