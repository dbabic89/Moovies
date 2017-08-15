package com.example.android.moovies.ui.movie_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.data.models.movie.MovieListResult;
import com.squareup.picasso.Picasso;

import java.util.List;

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w342/";
    private List<MovieListResult> movies;
    private int rowLayout;
    private Context context;

    MovieListAdapter(List<MovieListResult> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        int x = position + 1;

        MovieListResult movie = movies.get(position);

        String titleAndDate = movie.getTitle() + " (" + movie.getReleaseDate().substring(0, 4) + ")" ;
        holder.movieTitle.setText(titleAndDate);
        holder.movieDescription.setText(movie.getOverview());
        holder.moviePosition.setText(String.valueOf(x));
        holder.movieTmdbRating.setText(String.valueOf(movie.getVoteAverage()));

        Picasso.with(context).load(BASE_IMAGE_URL + movies.get(position).getPosterPath()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        TextView moviePosition;
        TextView movieTitle;
        TextView movieDescription;
        TextView movieTmdbRating;

        MovieViewHolder(View v) {
            super(v);
            moviePoster = (ImageView) v.findViewById(R.id.image_movie_poster);
            moviePosition = (TextView) v.findViewById(R.id.text_movie_position);
            movieTitle = (TextView) v.findViewById(R.id.text_movie_title);
            movieDescription = (TextView) v.findViewById(R.id.text_movie_description);
            movieTmdbRating = (TextView) v.findViewById(R.id.text_tmdb_rating);
        }
    }

}