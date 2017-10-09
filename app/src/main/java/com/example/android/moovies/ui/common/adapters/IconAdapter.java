package com.example.android.moovies.ui.common.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.MtvListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.android.moovies.utils.Constants.URL_ICON;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.MovieViewHolder> {

    private List<MtvListItem> listItems;
    private IconAdapter.RecyclerViewInterface recyclerViewInterface;

    @Inject
    Picasso picasso;

    @Inject
    IconAdapter() {
        listItems = new ArrayList<>();
    }

    @Override
    public IconAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_movie, parent, false);
        return new IconAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IconAdapter.MovieViewHolder holder, int position) {

        MtvListItem item = listItems.get(position);

        if (item.getPoster() == null)
            picasso.load(R.drawable.red_circle).into(holder.imageView);
        else picasso.load(URL_ICON + item.getPoster()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public MtvListItem getItem(int position) {
        return listItems.get(position);
    }


    public void add(MtvListItem movie) {
        listItems.add(movie);
        notifyItemInserted(listItems.size() - 1);
    }

    public void addAll(List<MtvListItem> movies) {
        for (MtvListItem movie : movies) {
            add(movie);
        }
    }

    public interface RecyclerViewInterface {

        void onCardClick(int position);

    }

    public void setRecyclerViewInterface(IconAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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