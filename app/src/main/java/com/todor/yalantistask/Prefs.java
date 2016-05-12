package com.todor.yalantistask;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.todor.yalantistask.model.User;

public class Prefs {

    private static final String TOKEN = "token";
    private static final String PROFILE_ICON = "profile_icon";
    private static final String USER = "user";
    private final SharedPreferences preferences;
    private Gson gson;
    private User user;

    public Prefs(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }

    public void saveFacebookToken(String facebookToken) {
        preferences.edit().putString(TOKEN, facebookToken).apply();
    }

    public boolean isFacebookTokenExists() {
        return !TextUtils.isEmpty(preferences.getString(TOKEN, null));
    }

    public void saveProfileIcon(String url) {
        preferences.edit().putString(PROFILE_ICON, url).apply();
    }

    public void saveCurrentUser(User currentUser) {
        preferences.edit().putString(USER, gson.toJson(currentUser)).apply();
    }

    @Nullable
    public User getUser() {
        if (user != null) return user;
        String userJson = preferences.getString(USER, "");
        if (!TextUtils.isEmpty(userJson)) {
            user = gson.fromJson(userJson, User.class);
        }
        return user;
    }

    public void clearAll() {
        preferences.edit().clear().apply();
    }

}
