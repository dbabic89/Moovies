package com.example.android.moovies.ui.common.mtv_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.mtv.MtvListItem;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.PaginationScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.moovies.utils.Constants.LIST_ID;


public class ListFragment extends Fragment implements ListMvpView {

    @BindView(R.id.recycler_view_movies)
    RecyclerView mRecyclerView;
    FragmentCommunication mFragmentCommunication;
    View mView;

    @Inject
    ListPresenter mPresenter;
    @Inject
    ListAdapter mListAdapter;

    @BindView(R.id.text_scroll_to_top)
    TextView textScrollToTop;

    private int currentPage, type, id, collectionId, tab;
    private int TOTAL_PAGES = 10;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    public static ListFragment newInstance(int id, int collectionId, int tab) {

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("collection_id", collectionId);
        bundle.putInt(LIST_ID, tab);

        ListFragment fragment = new ListFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            id = bundle.getInt("id");
            collectionId = bundle.getInt("collection_id");
            tab = bundle.getInt(LIST_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        currentPage = 1;

        readBundle(getArguments());
        createComponent();

        mView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, mView);

        mFragmentCommunication = (FragmentCommunication) getActivity();

        if (tab <= 3 || tab == 8 || tab == 10 || tab == 11 || tab == 12) type = 1;
        else type = 0;

        setPresenter(id, collectionId, tab);
        setRecyclerViewAndAdapter(id, collectionId, tab);

        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showList(List<MtvListItem> movies) {
        mListAdapter.addAll(movies);
    }

    @Override
    public void showListEmpty() {
        Toast.makeText(getActivity(), "Nema filmova", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTotalPages(int totalPages) {
        this.TOTAL_PAGES = totalPages;
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Nema interneta", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetails(int id) {
        if (type == 1) mFragmentCommunication.startMovieDetail(id);
        else mFragmentCommunication.startTvDetail(id);
    }

    private void createComponent() {

        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);
    }

    private void setPresenter(int id, int collectionId, int tab) {
        mPresenter.attachView(this);
        mPresenter.getList(currentPage, id, collectionId, tab);
    }

    private void setRecyclerViewAndAdapter(final int id, final int collectionId, final int tab) {

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mListAdapter.setRecyclerViewInterface(new ListAdapter.RecyclerViewInterface() {
            @Override
            public void onCardClick(int position) {
                MtvListItem listItem = mListAdapter.getItem(position);
                openDetails(listItem.getId());
            }
        });

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mListAdapter);

        if (collectionId == 0) {
            mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

                @Override
                protected void loadMoreItems() {
                    currentPage += 1;

                    if (currentPage <= TOTAL_PAGES) {
                        mPresenter.getList(currentPage, id, 0, tab);
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

            mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int visibility = (linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) ? View.VISIBLE : View.GONE;
                    textScrollToTop.setVisibility(visibility);
                    textScrollToTop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getActivity());
                            smoothScroller.setTargetPosition(0);
                            linearLayoutManager.startSmoothScroll(smoothScroller);
                        }
                    });
                }
            });
        }

    }


}
