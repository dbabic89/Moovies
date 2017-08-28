package com.example.android.moovies.ui.review_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.data.models.movie.Review;
import com.example.android.moovies.data.models.movie.Reviews;

public class ReviewListFragment extends Fragment {


    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_review_list, container, false);
        Reviews reviews = (Reviews) getArguments().getSerializable("reviews");

        for (Review re : reviews.getResults()) {
            Log.i("TAG", re.getAuthor());
        }
        ReviewAdapter reviewAdapter = new ReviewAdapter(reviews.getResults());

        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.reviews_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(reviewAdapter);



        return mView;
    }

}
