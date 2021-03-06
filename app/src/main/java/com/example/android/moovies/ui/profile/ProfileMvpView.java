package com.example.android.moovies.ui.profile;

import com.example.android.moovies.ui.base.BaseMvpView;

public interface ProfileMvpView extends BaseMvpView {

    void displayLogin();

    void startWebView(String token);

    void displayProfile(String sessionId);

}
