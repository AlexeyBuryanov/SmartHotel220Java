package com.alexeyburyanov.smarthotel.data.local.prefs;

import com.alexeyburyanov.smarthotel.data.DataManager;

/**
 * Created by Alexey Buryanov on 23.02.2018
 * Интерфейс описует методы по работе с настройками (SharedPreferences).
 */
public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    boolean getBooking();

    void setBooking(boolean isBooking);
}