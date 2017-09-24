package com.example.android.moovies.ui.common.mtv_list;

import com.example.android.moovies.domain.models.mtv.MtvListItem;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface ListMvpView extends BaseMvpView {

    void showList(List<MtvListItem> movies);

    void showListEmpty();

    void setTotalPages(int totalPages);

    void showError();

    void openDetails(int id);

}
