package com.example.android.moovies.ui.celebs_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;

public class CelebsListFragment extends Fragment {

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_celebs_list, container, false);



        return mView;
    }

}
