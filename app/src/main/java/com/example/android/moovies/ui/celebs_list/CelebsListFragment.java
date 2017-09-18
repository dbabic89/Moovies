package com.example.android.moovies.ui.celebs_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.Credits;
import com.example.android.moovies.utils.FragmentCommunication;

public class CelebsListFragment extends Fragment {

    View mView;
    FragmentCommunication fragmentCommunication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_celebs_list, container, false);

        fragmentCommunication = (FragmentCommunication) getActivity();
        final Credits credits = (Credits) getArguments().getSerializable("credits");

        createRecyclerView(credits);

        return mView;
    }

    private void createRecyclerView(final Credits credits) {

        CelebsListAdapter castAdapter = new CelebsListAdapter(credits.getCast(), getActivity());

        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view_celebs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(castAdapter);

        castAdapter.setRecyclerViewInterface(new CelebsListAdapter.RecyclerViewInterface() {
            @Override
            public void onCastClick(int position) {
                fragmentCommunication.startCelebrityDetail(credits.getCast().get(position).getId());
            }
        });
    }

}
