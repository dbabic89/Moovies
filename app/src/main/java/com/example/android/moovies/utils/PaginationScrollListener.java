package com.example.android.moovies.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;

    protected PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        int visibleItemCount = layoutManager.getChildCount();
//        Log.i("TAG", "visibleItemCount " + visibleItemCount);
        int totalItemCount = layoutManager.getItemCount();
//        Log.i("TAG", "totalItemCount" + totalItemCount);
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//        Log.i("TAG", "firstVisibleItemPosition" + firstVisibleItemPosition);
//        Log.i("TAG", "totalItemCount" + totalItemCount);

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 5
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}