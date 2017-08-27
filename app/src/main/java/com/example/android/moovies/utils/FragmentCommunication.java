package com.example.android.moovies.utils;

import com.example.android.moovies.data.models.movie.Review;

import java.util.ArrayList;

public interface FragmentCommunication {

    void startMovieTabs(int tab);

    void startMovieDetail(int id);

    void startReviewList(ArrayList<Review> reviewArrayList);
}
