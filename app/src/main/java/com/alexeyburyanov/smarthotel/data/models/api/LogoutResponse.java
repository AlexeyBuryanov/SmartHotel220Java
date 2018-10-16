package com.alexeyburyanov.smarthotel.data.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Ответ для выхода/логаута.
 */
public class LogoutResponse {

    @Expose
    @SerializedName("status_code")
    private String _statusCode;

    @Expose
    @SerializedName("message")
    private String _message;

    public String getStatusCode() { return _statusCode; }
    public void setStatusCode(String statusCode) { _statusCode = statusCode; }

    public String getMessage() { return _message; }
    public void setMessage(String message) { _message = message; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        LogoutResponse that = (LogoutResponse) object;

        if (_statusCode != null ? !_statusCode.equals(that._statusCode) : that._statusCode != null)
            return false;
        return _message != null ? _message.equals(that._message) : that._message == null;
    }

    @Override
    public int hashCode() {
        int result = _statusCode != null ? _statusCode.hashCode() : 0;
        result = 31 * result + (_message != null ? _message.hashCode() : 0);
        return result;
    }
}