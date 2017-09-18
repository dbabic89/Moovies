package com.example.android.moovies.ui.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.movie.MovieListResult;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.PaginationScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment implements SearchMvpView {

    View mView;
    @BindView(R.id.recycler_view_search) RecyclerView mRecyclerView;
    FragmentCommunication fragmentCommunication;

    @Inject
    SearchPresenter mPresenter;
    @Inject
    SearchAdapter searchAdapter;

    private int currentPage;
    private int TOTAL_PAGES = 10;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currentPage = 1;

        mView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, mView);
        fragmentCommunication = (FragmentCommunication) getActivity();

        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);
        setPresenter();

        return mView;
    }

    @Override
    public void showSearchResults(List<MovieListResult> movies) {
        searchAdapter.clear();
        searchAdapter.addAll(movies);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreMovies(List<MovieListResult> movies) {
        searchAdapter.addAll(movies);
    }

    @Override
    public void showMoviesEmpty() {
        if (mRecyclerView != null) mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Nema interneta", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openMovieDetails(int id) {
        fragmentCommunication.startSearchDetail(id);

    }

    public void searchMovies(String query) {
        setRecyclerViewAndAdapter(query);
        mPresenter.getMovies(query, currentPage);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void setPresenter() {
        mPresenter.attachView(this);
    }

    private void setRecyclerViewAndAdapter(final String query) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        searchAdapter.setRecyclerViewInterface(new SearchAdapter.RecyclerViewInterface() {
            @Override
            public void onCardClick(int position) {
                MovieListResult movieListResult = searchAdapter.getItem(position);
                openMovieDetails(movieListResult.getId());
            }
        });

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(searchAdapter);

        mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

            @Override
            protected void loadMoreItems() {
                currentPage += 1;

                if (currentPage <= TOTAL_PAGES) {
                    mPresenter.getMoreMovies(query, currentPage);
                }
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }
}
