package com.example.android.moovies.domain.observers;

import android.util.Log;

import com.example.android.moovies.domain.models.account.PostResponse;

import io.reactivex.observers.DisposableObserver;

public class AddToWatchlistObserver extends DisposableObserver<PostResponse> {
    @Override
    public void onNext(PostResponse value) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

}