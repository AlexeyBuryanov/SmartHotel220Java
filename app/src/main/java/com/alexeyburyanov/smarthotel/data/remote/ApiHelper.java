package com.alexeyburyanov.smarthotel.data.remote;

import com.alexeyburyanov.smarthotel.data.models.api.LoginRequest;
import com.alexeyburyanov.smarthotel.data.models.api.LoginResponse;
import com.alexeyburyanov.smarthotel.data.models.api.LogoutResponse;

import io.reactivex.Single;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Описывает помошника по работе с API.
 */
public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();
}