package com.example.android.moovies.ui.common.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.moovies.R;

import java.util.ArrayList;
import java.util.List;

public class ChipsAdapter extends RecyclerView.Adapter<ChipsAdapter.MovieViewHolder> {

    private List<String> keywords;

    public ChipsAdapter() {
        this.keywords = new ArrayList<>();
    }

    @Override
    public ChipsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chip, parent, false);
        return new ChipsAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChipsAdapter.MovieViewHolder holder, final int position) {
        holder.textKeyword.setText(keywords.get(position));
    }

    @Override
    public int getItemCount() {
        return keywords.size();
    }

    public void add(String keyword) {
        keywords.add(keyword);
        notifyItemInserted(keywords.size() - 1);
    }

    public void addAll(List<String> keywords) {

        for (String keyword : keywords) {
            add(keyword);
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView textKeyword;

        MovieViewHolder(View v) {
            super(v);
            textKeyword = (TextView) v.findViewById(R.id.text);
        }
    }


}