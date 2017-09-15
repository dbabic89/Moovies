package com.example.android.moovies.ui.movie_list;


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


public class MovieListFragment extends Fragment implements MovieListMvpView {

    RecyclerView mRecyclerView;
    FragmentCommunication fragmentCommunication;
    View mView;

    MovieComponent movieComponent;
    @Inject MovieListPresenter mPresenter;
    @Inject MovieListAdapter mMovieListAdapter;

    private int currentPage;
    private int TOTAL_PAGES = 10;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        currentPage  = 1;
        movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);

        mView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        fragmentCommunication = (FragmentCommunication) getActivity();

        int collectionId = getArguments().getInt("collection_id");
        int tab = getArguments().getInt("tab");
        setPresenter(collectionId, tab);
        setRecyclerViewAndAdapter(collectionId, tab);

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
    public void showProgress() {
//        mMovieListAdapter.addLoadingFooter();
    }

    @Override
    public void removeProgress() {
//        mMovieListAdapter.removeLoadingFooter();
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

    private void setPresenter(int collectionId, int tab) {
        mPresenter.attachView(this);
        mPresenter.getMovies(currentPage, 0, collectionId, tab);
    }

    private void setRecyclerViewAndAdapter(final int collectionId, final int tab) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mMovieListAdapter.setRecyclerViewInterface(new MovieListAdapter.RecyclerViewInterface() {
            @Override
            public void onCardClick(int position) {
                MovieListResult movieListResult = mMovieListAdapter.getItem(position);
                openMovieDetails(movieListResult.getId());
            }
        });

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view_movies);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMovieListAdapter);

        if (collectionId == 0) {
            mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

                @Override
                protected void loadMoreItems() {
                    currentPage += 1;

                    if (currentPage <= TOTAL_PAGES) {
                        mPresenter.getMovies(currentPage, 0, 0, tab);
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


}
