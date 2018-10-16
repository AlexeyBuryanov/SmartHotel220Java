package com.alexeyburyanov.smarthotel.data.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Запрос на вход.
 */
public class LoginRequest {

    private LoginRequest() {
        // Этот класс не является общедоступным
    }

    /**Запрос на обычный вход*/
    public static class ServerLoginRequest {
        @Expose
        @SerializedName("email")
        private String _email;

        @Expose
        @SerializedName("password")
        private String _password;

        public ServerLoginRequest(String email, String password) {
            _email = email;
            _password = password;
        }

        public String getEmail() { return _email; }
        public void setEmail(String email) { _email = email; }

        public String getPassword() { return _password; }
        public void setPassword(String password) { _password = password; }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            ServerLoginRequest that = (ServerLoginRequest) object;

            return (_email != null ? _email.equals(that._email) : that._email == null) &&
                    (_password != null ? _password.equals(that._password) : that._password == null);
        }

        @Override
        public int hashCode() {
            int result = _email != null ? _email.hashCode() : 0;
            result = 31 * result + (_password != null ? _password.hashCode() : 0);
            return result;
        }
    } // ServerLoginRequest class

    /**Запрос на вход с помощью g+*/
    public static class GoogleLoginRequest {
        @Expose
        @SerializedName("google_user_id")
        private String _googleUserId;

        @Expose
        @SerializedName("google_id_token")
        private String _idToken;

        public GoogleLoginRequest(String googleUserId, String idToken) {
            _googleUserId = googleUserId;
            _idToken = idToken;
        }

        public String getGoogleUserId() { return _googleUserId; }
        public void setGoogleUserId(String googleUserId) { _googleUserId = googleUserId; }

        public String getIdToken() { return _idToken; }
        public void setIdToken(String idToken) { _idToken = idToken; }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            GoogleLoginRequest that = (GoogleLoginRequest) object;

            if (_googleUserId != null ? !_googleUserId.equals(that._googleUserId)
                    : that._googleUserId != null)
                return false;
            return _idToken != null ? _idToken.equals(that._idToken) : that._idToken == null;
        }

        @Override
        public int hashCode() {
            int result = _googleUserId != null ? _googleUserId.hashCode() : 0;
            result = 31 * result + (_idToken != null ? _idToken.hashCode() : 0);
            return result;
        }
    } // GoogleLoginRequest class

    /**Запрос на вход с помощью fb*/
    public static class FacebookLoginRequest {
        @Expose
        @SerializedName("fb_user_id")
        private String _fbUserId;

        @Expose
        @SerializedName("fb_access_token")
        private String _fbAccessToken;

        public FacebookLoginRequest(String fbUserId, String fbAccessToken) {
            _fbUserId = fbUserId;
            _fbAccessToken = fbAccessToken;
        }

        public String getFbUserId() { return _fbUserId; }
        public void setFbUserId(String fbUserId) { _fbUserId = fbUserId; }

        public String getFbAccessToken() { return _fbAccessToken; }
        public void setFbAccessToken(String fbAccessToken) { _fbAccessToken = fbAccessToken; }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            FacebookLoginRequest that = (FacebookLoginRequest) object;

            if (_fbUserId != null ? !_fbUserId.equals(that._fbUserId) : that._fbUserId != null)
                return false;
            return _fbAccessToken != null ? _fbAccessToken.equals(that._fbAccessToken)
                    : that._fbAccessToken == null;
        }

        @Override
        public int hashCode() {
            int result = _fbUserId != null ? _fbUserId.hashCode() : 0;
            result = 31 * result + (_fbAccessToken != null ? _fbAccessToken.hashCode() : 0);
            return result;
        }
    } // FacebookLoginRequest class
} // LoginRequest