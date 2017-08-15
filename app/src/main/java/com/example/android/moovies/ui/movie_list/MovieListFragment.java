package com.example.android.moovies.ui.movie_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.moovies.R;
import com.example.android.moovies.data.models.movie.MovieListResult;

import java.util.List;


public class MovieListFragment extends Fragment implements MovieListMvpView {

    RecyclerView mRecyclerView;
    MovieListPresenter mPresenter;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.movies_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPresenter = new MovieListPresenter(getArguments().getInt("tab"));
        mPresenter.attachView(this);
        mPresenter.getMovies();

        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showMovies(List<MovieListResult> movies) {
        mRecyclerView.setAdapter(new MovieListAdapter(movies, R.layout.list_item_movie, getActivity()));
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

}
