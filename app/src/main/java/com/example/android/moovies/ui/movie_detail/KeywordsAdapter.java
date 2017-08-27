package com.example.android.moovies.ui.movie_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.moovies.R;

import java.util.List;

class KeywordsAdapter extends RecyclerView.Adapter<KeywordsAdapter.MovieViewHolder> {

    private List<String> keywords;

    KeywordsAdapter(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public KeywordsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chip_keyword, parent, false);
        return new KeywordsAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KeywordsAdapter.MovieViewHolder holder, final int position) {

        holder.textKeyword.setText(keywords.get(position));
    }

    @Override
    public int getItemCount() {
        return keywords.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView textKeyword;

        MovieViewHolder(View v) {
            super(v);
            textKeyword = (TextView) v.findViewById(R.id.text);
        }
    }


}