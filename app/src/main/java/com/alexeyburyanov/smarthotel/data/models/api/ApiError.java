package com.alexeyburyanov.smarthotel.data.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Представляет собой ошибку связанную с API данного приложения.
 */
public class ApiError {

    private int _errorCode;

    @Expose
    @SerializedName("status_code")
    private String _statusCode;

    @Expose
    @SerializedName("message")
    private String _message;

    public ApiError(int errorCode, String statusCode, String message) {
        _errorCode = errorCode;
        _statusCode = statusCode;
        _message = message;
    }

    public int getErrorCode() { return _errorCode; }
    public void setErrorCode(int errorCode) { _errorCode = errorCode; }

    public String getStatusCode() { return _statusCode; }
    public void setStatusCode(String statusCode) { _statusCode = statusCode; }

    public String getMessage() { return _message; }
    public void setMessage(String message) { _message = message; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ApiError apiError = (ApiError) object;

        if (_errorCode != apiError._errorCode) return false;
        if (_statusCode != null ? !_statusCode.equals(apiError._statusCode)
                : apiError._statusCode != null)
            return false;
        return _message != null ? _message.equals(apiError._message) : apiError._message == null;
    }

    @Override
    public int hashCode() {
        int result = _errorCode;
        result = 31 * result + (_statusCode != null ? _statusCode.hashCode() : 0);
        result = 31 * result + (_message != null ? _message.hashCode() : 0);
        return result;
    }
}