package com.alexeyburyanov.smarthotel.data;

import android.content.Context;

import com.alexeyburyanov.smarthotel.data.local.db.DbHelper;
import com.alexeyburyanov.smarthotel.data.local.prefs.PreferencesHelper;
import com.alexeyburyanov.smarthotel.data.models.api.LoginRequest;
import com.alexeyburyanov.smarthotel.data.models.api.LoginResponse;
import com.alexeyburyanov.smarthotel.data.models.api.LogoutResponse;
import com.alexeyburyanov.smarthotel.data.models.db.User;
import com.alexeyburyanov.smarthotel.data.remote.ApiHeader;
import com.alexeyburyanov.smarthotel.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 * Modified by Alexey Buryanov 19.02.2018.
 * Менеджер данных приложения. Реализует интерфейс DataManager.
 */
@Singleton
public class AppDataManager implements DataManager {

    private final Context _context;
    private final DbHelper _dbHelper;
    private final PreferencesHelper _preferencesHelper;
    private final ApiHelper _apiHelper;

    @Inject
    AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        _context = context;
        _dbHelper = dbHelper;
        _preferencesHelper = preferencesHelper;
        _apiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() { return _apiHelper.getApiHeader(); }

    @Override
    public String getAccessToken() { return _preferencesHelper.getAccessToken(); }

    @Override
    public void setAccessToken(String accessToken) {
        _preferencesHelper.setAccessToken(accessToken);
        _apiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public boolean getBooking() { return _preferencesHelper.getBooking(); }
    @Override
    public void setBooking(boolean isBooking) { _preferencesHelper.setBooking(isBooking); }

    @Override
    public Observable<Boolean> insertUser(User user) { return _dbHelper.insertUser(user); }

    @Override
    public Observable<Boolean> findUser(String name) { return _dbHelper.findUser(name); }

    @Override
    public Observable<List<User>> getAllUsers() { return _dbHelper.getAllUsers(); }

    @Override
    public Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return _apiHelper.doGoogleLoginApiCall(request);
    }
    @Override
    public Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return _apiHelper.doFacebookLoginApiCall(request);
    }
    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return _apiHelper.doServerLoginApiCall(request);
    }
    @Override
    public Single<LogoutResponse> doLogoutApiCall() { return _apiHelper.doLogoutApiCall(); }

    @Override
    public int getCurrentUserLoggedInMode() {
        return _preferencesHelper.getCurrentUserLoggedInMode();
    }
    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        _preferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public Long getCurrentUserId() { return _preferencesHelper.getCurrentUserId(); }
    @Override
    public void setCurrentUserId(Long userId) { _preferencesHelper.setCurrentUserId(userId); }

    @Override
    public String getCurrentUserName() { return _preferencesHelper.getCurrentUserName(); }
    @Override
    public void setCurrentUserName(String userName) {
        _preferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserEmail() { return _preferencesHelper.getCurrentUserEmail(); }
    @Override
    public void setCurrentUserEmail(String email) { _preferencesHelper.setCurrentUserEmail(email); }

    @Override
    public String getCurrentUserProfilePicUrl() { return _preferencesHelper.getCurrentUserProfilePicUrl(); }
    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        _preferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        _apiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        _apiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, LoggedInMode loggedInMode,
                               String userName, String email, String profilePicPath) {
        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserEmail(email);
        setCurrentUserProfilePicUrl(profilePicPath);

        updateApiHeader(userId, accessToken);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(null, null, DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null, null, null);
    }
}