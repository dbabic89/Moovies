package com.example.android.moovies.ui.home_movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.ui.movie_list.HorizontalRecyclerView;

public class HomeMovieFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();

        Fragment nowPlaying = new HorizontalRecyclerView();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("tab", 0);
        nowPlaying.setArguments(bundle1);

        Fragment upcoming = new HorizontalRecyclerView();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("tab", 1);
        upcoming.setArguments(bundle2);

        Fragment popular = new HorizontalRecyclerView();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("tab", 2);
        popular.setArguments(bundle3);

        Fragment topRated = new HorizontalRecyclerView();
        Bundle bundle4 = new Bundle();
        bundle4.putInt("tab", 3);
        topRated.setArguments(bundle4);

        fragmentManager.beginTransaction().replace(R.id.now_playing, nowPlaying).commit();
        fragmentManager.beginTransaction().replace(R.id.upcoming, upcoming).commit();
        fragmentManager.beginTransaction().replace(R.id.popular, popular).commit();
        fragmentManager.beginTransaction().replace(R.id.top_rated, topRated).commit();

        return view;
    }

}
