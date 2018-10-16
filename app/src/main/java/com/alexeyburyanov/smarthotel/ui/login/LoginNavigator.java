package com.alexeyburyanov.smarthotel.ui.login;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public interface LoginNavigator {

    void openMainActivity();
    void handleError(Throwable throwable);
    void login();
}