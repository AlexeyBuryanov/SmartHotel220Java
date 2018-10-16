package com.alexeyburyanov.smarthotel.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.di.PreferenceInfo;
import com.alexeyburyanov.smarthotel.utils.AppConstants;

import javax.inject.Inject;

/**
 * Created by Alexey Buryanov on 23.02.2018
 * Класс-помошник по работе с настройками SharedPreferences. Реализует PreferencesHelper.
 */
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_ACCESS_BOOKING = "PREF_KEY_ACCESS_BOOKING";

    private final SharedPreferences _prefs;

    @Inject
    AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        _prefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public Long getCurrentUserId() {
        long userId = _prefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }
    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        _prefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public String getCurrentUserName() {
        return _prefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }
    @Override
    public void setCurrentUserName(String userName) {
        _prefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return _prefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }
    @Override
    public void setCurrentUserEmail(String email) {
        _prefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return _prefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }
    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        _prefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return _prefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }
    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        _prefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getAccessToken() { return _prefs.getString(PREF_KEY_ACCESS_TOKEN, null); }
    @Override
    public void setAccessToken(String accessToken) {
        _prefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public boolean getBooking() {
        return _prefs.getBoolean(PREF_KEY_ACCESS_BOOKING, false);
    }
    @Override
    public void setBooking(boolean isBooking) {
        _prefs.edit().putBoolean(PREF_KEY_ACCESS_BOOKING, isBooking).apply();
    }
}