package com.example.android.moovies.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.ui.common.mtv_list.HorizontalRecyclerView;
import com.example.android.moovies.utils.Constants;

import java.util.Arrays;
import java.util.List;

public class HomeMtvFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_movie, container, false);

        if (getArguments().getString(Constants.HOME_MOVIE_FRAGMENT) != null) {

            List<Integer> listIds = Arrays.asList(0,1,2,3);
            startHorizontalViews(listIds);

        } else if (getArguments().getString(Constants.HOME_TV_FRAGMENT) != null) {

            List<Integer> listIds = Arrays.asList(4,5,6,7);
            startHorizontalViews(listIds);

        }
        return view;
    }

    private void startHorizontalViews(List<Integer> listIds ) {

        FragmentManager fragmentManager = getChildFragmentManager();

        Fragment nowPlaying = new HorizontalRecyclerView();
        Bundle bundle1 = new Bundle();
        bundle1.putInt(Constants.LIST_ID, listIds.get(0));
        nowPlaying.setArguments(bundle1);

        Fragment upcoming = new HorizontalRecyclerView();
        Bundle bundle2 = new Bundle();
        bundle2.putInt(Constants.LIST_ID, listIds.get(1));
        upcoming.setArguments(bundle2);

        Fragment popular = new HorizontalRecyclerView();
        Bundle bundle3 = new Bundle();
        bundle3.putInt(Constants.LIST_ID, listIds.get(2));
        popular.setArguments(bundle3);

        Fragment topRated = new HorizontalRecyclerView();
        Bundle bundle4 = new Bundle();
        bundle4.putInt(Constants.LIST_ID, listIds.get(3));
        topRated.setArguments(bundle4);

        fragmentManager.beginTransaction().replace(R.id.now_playing, nowPlaying).commit();
        fragmentManager.beginTransaction().replace(R.id.upcoming, upcoming).commit();
        fragmentManager.beginTransaction().replace(R.id.popular, popular).commit();
        fragmentManager.beginTransaction().replace(R.id.top_rated, topRated).commit();
    }

}
