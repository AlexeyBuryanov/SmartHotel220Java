package com.alexeyburyanov.smarthotel.data.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Ответ для входа/логина.
 */
public class LoginResponse {

    @Expose
    @SerializedName("status_code")
    private String _statusCode;

    @Expose
    @SerializedName("user_id")
    private Long _userId;

    @Expose
    @SerializedName("access_token")
    private String _accessToken;

    @Expose
    @SerializedName("user_name")
    private String _userName;

    @Expose
    @SerializedName("email")
    private String _userEmail;

    @Expose
    @SerializedName("server_profile_pic_url")
    private String _serverProfilePicUrl;

    @Expose
    @SerializedName("fb_profile_pic_url")
    private String _fbProfilePicUrl;

    @Expose
    @SerializedName("google_profile_pic_url")
    private String _googleProfilePicUrl;

    @Expose
    @SerializedName("message")
    private String _message;

    public String getStatusCode() { return _statusCode; }
    public void setStatusCode(String statusCode) { _statusCode = statusCode; }

    public Long getUserId() { return _userId; }
    public void setUserId(Long userId) { _userId = userId; }

    public String getAccessToken() { return _accessToken; }
    public void setAccessToken(String accessToken) { _accessToken = accessToken; }

    public String getMessage() { return _message; }
    public void setMessage(String message) { _message = message; }

    public String getUserName() { return _userName; }
    public void setUserName(String userName) { _userName = userName; }

    public String getUserEmail() { return _userEmail; }
    public void setUserEmail(String userEmail) { _userEmail = userEmail; }

    public String getFbProfilePicUrl() { return _fbProfilePicUrl; }
    public void setFbProfilePicUrl(String fbProfilePicUrl) { _fbProfilePicUrl = fbProfilePicUrl; }

    public String getGoogleProfilePicUrl() { return _googleProfilePicUrl; }
    public void setGoogleProfilePicUrl(String googleProfilePicUrl) { _googleProfilePicUrl = googleProfilePicUrl; }

    public String getServerProfilePicUrl() { return _serverProfilePicUrl; }
    public void setServerProfilePicUrl(String serverProfilePicUrl) { _serverProfilePicUrl = serverProfilePicUrl; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        LoginResponse that = (LoginResponse) object;

        if (_statusCode != null ? !_statusCode.equals(that._statusCode) : that._statusCode != null)
            return false;
        if (_userId != null ? !_userId.equals(that._userId) : that._userId != null) return false;
        if (_accessToken != null ? !_accessToken.equals(that._accessToken) : that._accessToken != null)
            return false;
        if (_userName != null ? !_userName.equals(that._userName) : that._userName != null)
            return false;
        if (_userEmail != null ? !_userEmail.equals(that._userEmail) : that._userEmail != null)
            return false;
        if (_serverProfilePicUrl != null ? !_serverProfilePicUrl.equals(that._serverProfilePicUrl)
                : that._serverProfilePicUrl != null)
            return false;
        if (_fbProfilePicUrl != null ? !_fbProfilePicUrl.equals(that._fbProfilePicUrl)
                : that._fbProfilePicUrl != null)
            return false;
        if (_googleProfilePicUrl != null ? !_googleProfilePicUrl.equals(that._googleProfilePicUrl)
                : that._googleProfilePicUrl != null)
            return false;
        return _message != null ? _message.equals(that._message) : that._message == null;
    }

    @Override
    public int hashCode() {
        int result = _statusCode != null ? _statusCode.hashCode() : 0;
        result = 31 * result + (_userId != null ? _userId.hashCode() : 0);
        result = 31 * result + (_accessToken != null ? _accessToken.hashCode() : 0);
        result = 31 * result + (_userName != null ? _userName.hashCode() : 0);
        result = 31 * result + (_userEmail != null ? _userEmail.hashCode() : 0);
        result = 31 * result + (_serverProfilePicUrl != null ? _serverProfilePicUrl.hashCode() : 0);
        result = 31 * result + (_fbProfilePicUrl != null ? _fbProfilePicUrl.hashCode() : 0);
        result = 31 * result + (_googleProfilePicUrl != null ? _googleProfilePicUrl.hashCode() : 0);
        result = 31 * result + (_message != null ? _message.hashCode() : 0);
        return result;
    }
}