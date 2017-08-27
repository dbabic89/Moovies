package com.example.android.moovies.ui.common.image_slider;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moovies.R;
import com.squareup.picasso.Picasso;

public class ImageSliderFragment extends Fragment {

    public static ImageSliderFragment newInstance(String pic) {

        ImageSliderFragment fragment = new ImageSliderFragment();
        Bundle args = new Bundle();
        args.putString("index", pic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_image_slider, container, false);
        Bundle args = getArguments();
        String index = args.getString("index", "");
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
        Picasso.with(getActivity()).load("https://image.tmdb.org/t/p/w1280/" + index).into(imageView);
        return rootView;
    }
}