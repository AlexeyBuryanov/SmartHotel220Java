package com.alexeyburyanov.smarthotel.utils;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * Различные константы приложения.
 */
public final class AppConstants {

    private AppConstants() {
        // Этот класс утилиты не является общедоступным
    }

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "smart_hotel_220.db";
    public static final String PREF_NAME = "smart_hotel_220_pref";

    public static final long NULL_INDEX = -1L;
}