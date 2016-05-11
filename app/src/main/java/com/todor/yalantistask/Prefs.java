package com.todor.yalantistask;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class Prefs {

    private static final String TOKEN = "token";
    private final SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setFacebookToken(String facebookToken) {
        preferences.edit().putString(TOKEN, facebookToken).apply();
    }

    public boolean isFacebookTokenExists() {
        return !TextUtils.isEmpty(preferences.getString(TOKEN, null));
    }

    public void clearAll() {
        preferences.edit().clear().apply();
    }

}
