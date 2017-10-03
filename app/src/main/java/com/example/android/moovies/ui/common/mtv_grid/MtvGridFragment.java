package com.example.android.moovies.ui.common.mtv_grid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.celebrity.Posters;
import com.example.android.moovies.utils.FragmentCommunication;

public class MtvGridFragment extends Fragment {

    View mView;
    FragmentCommunication fragmentCommunication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_poster_grid, container, false);

        fragmentCommunication = (FragmentCommunication) getActivity();

        Posters posters = (Posters) getArguments().getSerializable("movies");
        createRecyclerViewAndAdapter(posters);

        return mView;
    }

    private void createRecyclerViewAndAdapter(final Posters posters) {

        MtvGridAdapter mtvGridAdapter = new MtvGridAdapter(posters, getActivity());
        mtvGridAdapter.setRecyclerViewInterface(new MtvGridAdapter.RecyclerViewInterface() {
            @Override
            public void onCardClick(int position) {
                fragmentCommunication.startMovieDetail(posters.getMtvPosterList().get(position).getId());
            }
        });

        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mtvGridAdapter);
    }
}
