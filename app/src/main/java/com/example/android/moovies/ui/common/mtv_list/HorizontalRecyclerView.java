package com.example.android.moovies.ui.common.mtv_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.mtv.MtvListItem;
import com.example.android.moovies.domain.use_case.GetMovieList;
import com.example.android.moovies.domain.use_case.GetTvList;
import com.example.android.moovies.ui.common.adapters.IconAdapter;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.FragmentCommunication;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalRecyclerView extends Fragment implements ListMvpView, View.OnClickListener {

    @BindView(R.id.text_movie_list)
    TextView mTextMovieList;

    @BindView(R.id.recycler_view_horizontal)
    RecyclerView mRecyclerView;

    @BindView(R.id.button_see_more)
    Button buttonSeeMore;

    @Inject
    ListPresenter mPresenter;

    @Inject
    GetMovieList getMovieList;

    @Inject
    GetTvList getTvList;

    @Inject
    IconAdapter mIconAdapter;

    private FragmentCommunication mFragmentCommunication;

    View mView;
    int currentRv, itemId, type = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.view_horizontal_list, container, false);
        ButterKnife.bind(this, mView);

        mFragmentCommunication = (FragmentCommunication) getActivity();

        createComponent();

        currentRv = getArguments().getInt(Constants.LIST_ID);

        if (currentRv < 4 || currentRv == 8) type = 1;
        else type = 0;

        itemId = getArguments().getInt("movie_id");

        setPresenter();
        setRecyclerViewAndAdapter();

        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMovieList.dispose();
        getTvList.dispose();
    }

    @Override
    public void showList(List<MtvListItem> listItems) {
        mIconAdapter.addAll(listItems);
    }

    @Override
    public void showListEmpty() {
        Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTotalPages(int totalPages) {

    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "No internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetails(int id) {
        if (type == 1) mFragmentCommunication.startMovieDetail(id);
        else mFragmentCommunication.startTvDetail(id);
    }

    @Override
    public void onClick(View view) {
        if (currentRv == 8 || currentRv == 9) {
            mFragmentCommunication.startList(itemId, currentRv);
        } else mFragmentCommunication.startTabs(currentRv, type);
    }

    private void createComponent() {

        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);
    }

    private void setPresenter() {
        mPresenter.getList(1, itemId, 0, currentRv);
        mPresenter.attachView(this);
    }

    private void setRecyclerViewAndAdapter() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mIconAdapter);
        mIconAdapter.setRecyclerViewInterface(new IconAdapter.RecyclerViewInterface() {
            @Override
            public void onCardClick(int position) {
                MtvListItem mtvListItem = mIconAdapter.getItem(position);
                openDetails(mtvListItem.getId());
            }
        });

        List<String> movieListNames = Arrays.asList("Now", "Upcoming", "Popular", "Top rated", "Today", "On air", "Popular", "Top rated", "Similar", "Similar");
        mTextMovieList.setText(movieListNames.get(currentRv));

        buttonSeeMore.setOnClickListener(this);
    }

}
