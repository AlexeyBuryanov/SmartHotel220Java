package com.alexeyburyanov.smarthotel.ui.login;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.data.models.api.LoginRequest;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.CommonUtils;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onServerLoginClick() {
        setIsLoading(true);
        getNavigator().login();
        setIsLoading(false);
    }

    /**Google+*/
    public void onGoogleLoginClick() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGoogleLoginApiCall(new LoginRequest.GoogleLoginRequest("test1", "test1"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getDataManager().updateUserInfo(
                            response.getAccessToken(),
                            response.getUserId(),
                            DataManager.LoggedInMode.LOGGED_IN_MODE_GOOGLE,
                            response.getUserName(),
                            response.getUserEmail(),
                            response.getGoogleProfilePicUrl());
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    /**Facebook*/
    public void onFbLoginClick() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doFacebookLoginApiCall(new LoginRequest.FacebookLoginRequest("test3", "test4"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getDataManager().updateUserInfo(
                            response.getAccessToken(),
                            response.getUserId(),
                            DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                            response.getUserName(),
                            response.getUserEmail(),
                            response.getFbProfilePicUrl());
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    /**Обычный вход*/
    public void login(String email, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getDataManager().updateUserInfo(
                            response.getAccessToken(),
                            response.getUserId(),
                            DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                            response.getUserName(),
                            response.getUserEmail(),
                            response.getServerProfilePicUrl());
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    boolean isEmailEmpty(String email) { return !(email == null || email.isEmpty()); }
    boolean isEmailValid(String email) { return CommonUtils.isEmailValid(email); }
    boolean isPasswordEmpty(String password) { return !(password == null || password.isEmpty()); }
}