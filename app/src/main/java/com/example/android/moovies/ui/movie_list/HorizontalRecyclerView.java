package com.example.android.moovies.ui.movie_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moovies.R;
import com.example.android.moovies.data.models.movie.MovieListResult;
import com.example.android.moovies.ui.common.IconAdapter;
import com.example.android.moovies.utils.FragmentCommunication;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalRecyclerView extends Fragment implements MovieListMvpView, View.OnClickListener {

    @BindView(R.id.text_movie_list)
    TextView mTextMovieList;

    @BindView(R.id.recycler_view_horizontal)
    RecyclerView mRecyclerView;

    @BindView(R.id.button_see_more)
    Button buttonSeeMore;

    int currentRv;

    FragmentCommunication fragmentCommunication;
    MovieListPresenter mPresenter;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.horizontal_list, container, false);
        ButterKnife.bind(this, mView);

        currentRv = getArguments().getInt("tab");
        Log.i("TAG", "horizontal primio: " + getArguments().getInt("tab"));

        if (mPresenter == null) {
            mPresenter = new MovieListPresenter(currentRv);
        }
        mPresenter.attachView(this);
        mPresenter.getMovies(1);

        fragmentCommunication = (FragmentCommunication) getActivity();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        List<String> movieListNames = Arrays.asList("Now", "Upcoming", "Popular", "Top rated");
        mTextMovieList.setText(movieListNames.get(currentRv));

        buttonSeeMore.setOnClickListener(this);

        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mPresenter.detachView();
    }

    @Override
    public void showMovies(List<MovieListResult> movies) {
        mRecyclerView.setAdapter(new IconAdapter(movies, R.layout.icon_movie, getActivity()));
    }

    @Override
    public void showMovieProgress(boolean show) {

    }

    @Override
    public void showMoviesEmpty() {

        Toast.makeText(getActivity(), "Nema filmova", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Nema interneta", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void openMovieDetails(int id) {
    }

    @Override
    public void onClick(View view) {

        fragmentCommunication.startMovieTabs(currentRv);
    }
}
