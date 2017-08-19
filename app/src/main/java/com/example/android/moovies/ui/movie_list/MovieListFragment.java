package com.example.android.moovies.ui.movie_list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.moovies.R;
import com.example.android.moovies.data.models.movie.MovieListResult;
import com.example.android.moovies.ui.movie_detail.MovieDetailActivity;

import java.util.List;


public class MovieListFragment extends Fragment implements MovieListMvpView {

    RecyclerView mRecyclerView;
    MovieListPresenter mPresenter;
    MovieListAdapter mMovieListAdapter;
    View mView;
    List<MovieListResult> movies;

    private int currentPage = 1;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.movies_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mMovieListAdapter = new MovieListAdapter(getActivity());
        mRecyclerView.setAdapter(mMovieListAdapter);

        mMovieListAdapter.setRecyclerViewInterface(new MovieListAdapter.RecyclerViewInterface() {
            @Override
            public void onCardClick(int position) {
                MovieListResult movieListResult = mMovieListAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movie_id", movieListResult.getId());
                startActivity(intent);
            }
        });

        mPresenter = new MovieListPresenter(getArguments().getInt("tab"));
        Log.i("TAG", "MovieListFragment primio: " + getArguments().getInt("tab"));
        mPresenter.attachView(this);
        mPresenter.getMovies(1);

//        mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
//
//            @Override
//            protected void loadMoreItems() {
//                currentPage += 1;
//                Log.i("TAG", "currentPage" + currentPage);
//                mPresenter.getMovies(2);
//            }
//
//            @Override
//            public int getTotalPageCount() {
//                return TOTAL_PAGES;
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });

        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showMovies(List<MovieListResult> movies) {
        mMovieListAdapter.addAll(movies);
        for (MovieListResult mo :movies) {
//            Log.i("TAG", currentPage + "movie " + mo.getTitle());
            
        }
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
