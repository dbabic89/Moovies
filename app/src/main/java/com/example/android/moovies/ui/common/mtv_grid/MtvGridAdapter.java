package com.example.android.moovies.ui.common.mtv_grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.celebrity.Posters;
import com.example.android.moovies.utils.Constants;
import com.squareup.picasso.Picasso;

class MtvGridAdapter extends RecyclerView.Adapter<MtvGridAdapter.MovieViewHolder> {

    private Posters posterList;
    private Context context;
    private MtvGridAdapter.RecyclerViewInterface recyclerViewInterface;

    MtvGridAdapter(Posters posterList, Context context) {
        this.posterList = posterList;
        this.context = context;
    }

    public void setRecyclerViewInterface(MtvGridAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_poster, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Picasso.with(context).load(Constants.URL_POSTER + posterList.getMtvPosterList().get(position).getPosterPath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return posterList.getMtvPosterList().size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        MovieViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image_poster);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCardClick(getAdapterPosition());
        }

    }

    interface RecyclerViewInterface {

        void onCardClick(int position);

    }
}