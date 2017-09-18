package com.example.android.moovies.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferencesManager {

    private static final String PREF_NAME = "MooviesPref";
    private static final String KEY_LOGIN = "isLoggedIn";
    private static final String KEY_ACCOUNT_ID = "accountId";
    private static final String KEY_SESSION_ID = "sessionId";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Inject
    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = sharedPreferences.edit();
    }

    public void createLoggingSession(String sessionId) {
        editor.putBoolean(KEY_LOGIN, true);
        editor.putString(KEY_SESSION_ID, sessionId);
        editor.commit();
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }

    public String getSessionId() {
        return sharedPreferences.getString(KEY_SESSION_ID, "");
    }

    public int getAccountId() {
        return sharedPreferences.getInt(KEY_ACCOUNT_ID, 0);
    }

    public void setAccountId(int accountId) {
        editor.putInt(KEY_ACCOUNT_ID, accountId).commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }

}
