package com.example.android.moovies.ui.review_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.movie.Reviews;

public class ReviewListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_review_list, container, false);

        Reviews reviews = (Reviews) getArguments().getSerializable("reviews");
        ReviewAdapter reviewAdapter = new ReviewAdapter(reviews.getResults());
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.reviews_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(reviewAdapter);

        return mView;
    }

}
