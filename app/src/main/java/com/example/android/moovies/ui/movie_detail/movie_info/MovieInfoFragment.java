package com.example.android.moovies.ui.movie_detail.movie_info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieInfoFragment extends Fragment {

    @BindView(R.id.imageView)
    ImageView imageView;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_movie_info, container, false);

        ButterKnife.bind(getActivity(), mView);

        return mView;
    }

}
