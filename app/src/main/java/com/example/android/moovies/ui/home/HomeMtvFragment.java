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

    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_movie, container, false);

        fragmentManager= getChildFragmentManager();

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

        List<Integer> fragment_ids = Arrays.asList(R.id.now_playing, R.id.upcoming, R.id.popular, R.id.top_rated);

        for (int i = 0; i < listIds.size(); i++) {

            Fragment fragment = new HorizontalRecyclerView();
            Bundle bundle1 = new Bundle();
            bundle1.putInt(Constants.LIST_ID, listIds.get(i));
            fragment.setArguments(bundle1);

            fragmentManager.beginTransaction().replace(fragment_ids.get(i), fragment).commit();
        }
    }

}
