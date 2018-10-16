package com.alexeyburyanov.smarthotel.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.alexeyburyanov.smarthotel.di.ApiInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Для доступа к API, как публичному так и защищённому.
 */
@Singleton
public class ApiHeader {

    private ProtectedApiHeader _protectedApiHeader;
    private PublicApiHeader _publicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        _publicApiHeader = publicApiHeader;
        _protectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() { return _protectedApiHeader; }
    public PublicApiHeader getPublicApiHeader() { return _publicApiHeader; }

    public static final class PublicApiHeader {

        @Expose
        @SerializedName("api_key")
        private String _apiKey;

        @Inject
        public PublicApiHeader(@ApiInfo String apiKey) { _apiKey = apiKey; }

        public String getApiKey() { return _apiKey; }
        public void setApiKey(String apiKey) { _apiKey = apiKey; }
    } // PublicApiHeader class

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("api_key")
        private String _apiKey;

        @Expose
        @SerializedName("user_id")
        private Long _userId;

        @Expose
        @SerializedName("access_token")
        private String _accessToken;

        public ProtectedApiHeader(String mApiKey, Long mUserId, String mAccessToken) {
            _apiKey = mApiKey;
            _userId = mUserId;
            _accessToken = mAccessToken;
        }

        public String getApiKey() { return _apiKey; }
        public void setApiKey(String apiKey) { _apiKey = apiKey; }

        public Long getUserId() { return _userId; }
        public void setUserId(Long mUserId) { _userId = mUserId; }

        public String getAccessToken() { return _accessToken; }
        public void setAccessToken(String accessToken) { _accessToken = accessToken; }
    } // ProtectedApiHeader class
}