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
import com.example.android.moovies.utils.FragmentCommunication;

import java.util.List;


public class MovieListFragment extends Fragment implements MovieListMvpView {

    RecyclerView mRecyclerView;
    MovieListPresenter mPresenter;
    MovieListAdapter mMovieListAdapter;
    FragmentCommunication fragmentCommunication;
    View mView;
    List<MovieListResult> movies;

    private int currentPage = 1;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        fragmentCommunication = (FragmentCommunication) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mMovieListAdapter = new MovieListAdapter(getActivity());

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.movies_recycler_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMovieListAdapter);

        mMovieListAdapter.setRecyclerViewInterface(new MovieListAdapter.RecyclerViewInterface() {
            @Override
            public void onCardClick(int position) {
                MovieListResult movieListResult = mMovieListAdapter.getItem(position);
                openMovieDetails(movieListResult.getId());

            }
        });

        int collectionId = getArguments().getInt("collection_id");

        mPresenter = new MovieListPresenter(getArguments().getInt("tab"));
        mPresenter.attachView(this);
        mPresenter.getMovies(1, 0, collectionId);

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
        fragmentCommunication.startMovieDetail(id);
    }
}
