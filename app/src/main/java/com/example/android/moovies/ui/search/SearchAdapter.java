package com.example.android.moovies.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.search.SearchListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.android.moovies.utils.Constants.URL_POSTER;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MtvViewHolder> {

    private SearchAdapter.RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private List<SearchListItem> items;

    @Inject
    SearchAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public MtvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_search, parent, false);
        return new MtvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MtvViewHolder holder, final int position) {
        SearchListItem item = items.get(position);
        holder.title.setText(item.getTitle());
        Picasso.with(context).load(URL_POSTER + item.getPoster()).into(holder.poster);
        holder.type.setText(item.getType());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(SearchListItem item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    void addAll(List<SearchListItem> items) {
        for (SearchListItem item : items) {
            add(item);
        }
    }

    void clear() {
        items = new ArrayList<>();
    }

    SearchListItem getItem(int position) {
        return items.get(position);
    }

    interface RecyclerViewInterface {
        void onCardClick(int position);
    }

    void setRecyclerViewInterface(SearchAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    class MtvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView poster;
        TextView title;
        TextView type;

        MtvViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            poster = (ImageView) v.findViewById(R.id.image_poster);
            title = (TextView) v.findViewById(R.id.text_title);
            type = (TextView) v.findViewById(R.id.text_type);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCardClick(getAdapterPosition());
        }
    }
}