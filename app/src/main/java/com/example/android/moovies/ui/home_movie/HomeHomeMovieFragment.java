package com.example.android.moovies.ui.home_movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.moovies.R;
import com.example.android.moovies.utils.FragmentCommunication;

public class HomeHomeMovieFragment extends Fragment implements HomeMovieMvpView {

    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4;
    HomeMoviePresenter mPresenter;

    FragmentCommunication fragmentCommunication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        mPresenter = new HomeMoviePresenter(getActivity());
        mPresenter.attachView(this);
        fragmentCommunication = (FragmentCommunication) getActivity();

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentCommunication.startMovieTabs();
            }
        });

        CardView cardView1 = (CardView) view.findViewById(R.id.horizontal_now_playing);
        CardView cardView2 = (CardView) view.findViewById(R.id.horizontal_upcoming);
        CardView cardView3 = (CardView) view.findViewById(R.id.horizontal_popular);
        CardView cardView4 = (CardView) view.findViewById(R.id.horizontal_top_rated);

        recyclerView1 = (RecyclerView) cardView1.findViewById(R.id.recycler_view_horizontal);
        recyclerView2 = (RecyclerView) cardView2.findViewById(R.id.recycler_view_horizontal);
        recyclerView3 = (RecyclerView) cardView3.findViewById(R.id.recycler_view_horizontal);
        recyclerView4 = (RecyclerView) cardView4.findViewById(R.id.recycler_view_horizontal);

        mPresenter.getNowPlayingMovies(recyclerView1);
        mPresenter.getUpcomingMovies(recyclerView2);
        mPresenter.getPopularMovies(recyclerView3);
        mPresenter.getTopratedMovies(recyclerView4);

//        new HorizontalRecyclerView(getActivity(), 1, linearLayout, "Now");
//        for (int i = 0; i < 4; i++) {
//
//            List<String> titles = asList("Now", "Upcoming", "Popular", "Top 200");
//
//            new HorizontalRecyclerView(getActivity(), i, linearLayout, titles.get(i));
//        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showNowPlayingMovies() {

    }

    @Override
    public void showUpcomingMovies() {
    }

    @Override
    public void showPopularMovies() {
    }

    @Override
    public void showTopRatedMovies() {
    }
}
