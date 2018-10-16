package com.alexeyburyanov.smarthotel.data.remote;

import com.alexeyburyanov.smarthotel.data.models.api.LoginRequest;
import com.alexeyburyanov.smarthotel.data.models.api.LoginResponse;
import com.alexeyburyanov.smarthotel.data.models.api.LogoutResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Помошник по работе с API. Реализует интерфейс ApiHelper.
 */
@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader _apiHeader;

    @Inject
    AppApiHelper(ApiHeader apiHeader) { _apiHeader = apiHeader; }

    @Override
    public ApiHeader getApiHeader() { return _apiHeader; }

    @Override
    public Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return Single.fromCallable(() -> {
            LoginResponse response = new LoginResponse();
            response.setUserName("Facebook UserName");
            response.setUserEmail("facebook@mail.com");
            response.setUserId(1L);
            response.setAccessToken(request.getIdToken());
            response.setFbProfilePicUrl(null);
            response.setGoogleProfilePicUrl(null);
            response.setStatusCode("okay");
            response.setMessage("login");
            return response;
        });
        //return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
        //        .addHeaders(_apiHeader.getPublicApiHeader())
        //        .addBodyParameter(request)
        //        .build()
        //        .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return Single.fromCallable(() -> {
            LoginResponse response = new LoginResponse();
            response.setUserName("Google UserName");
            response.setUserEmail("google@gmail.com");
            response.setUserId(1L);
            response.setAccessToken(request.getFbAccessToken());
            response.setFbProfilePicUrl(null);
            response.setGoogleProfilePicUrl(null);
            response.setStatusCode("okay");
            response.setMessage("login");
            return response;
        });
        //return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
        //        .addHeaders(_apiHeader.getPublicApiHeader())
        //        .addBodyParameter(request)
        //        .build()
        //        .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return Single.fromCallable(() -> {
            LoginResponse response = new LoginResponse();
            response.setUserName(request.getEmail());
            response.setUserEmail(request.getEmail());
            response.setUserId(1L);
            response.setAccessToken("ACCESS_TOKEN");
            response.setFbProfilePicUrl(null);
            response.setGoogleProfilePicUrl(null);
            response.setStatusCode("okay");
            response.setMessage("login");
            return response;
        });
        //return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
        //        .addHeaders(_apiHeader.getPublicApiHeader())
        //        .addBodyParameter(request)
        //        .build()
        //        .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return Single.fromCallable(() -> {
            LogoutResponse response = new LogoutResponse();
            response.setStatusCode("okay");
            response.setMessage("logout");
            return response;
        });
        //return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
        //        .addHeaders(_apiHeader.getProtectedApiHeader())
        //        .build()
        //        .getObjectSingle(LogoutResponse.class);
    }
}