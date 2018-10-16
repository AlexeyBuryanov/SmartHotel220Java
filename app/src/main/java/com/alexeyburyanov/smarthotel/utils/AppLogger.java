package com.alexeyburyanov.smarthotel.utils;

import com.alexeyburyanov.smarthotel.BuildConfig;

import timber.log.Timber;

/**
 * Created by amitshekhar on 07/07/17.
 */
public final class AppLogger {

    // Это класс утилиты и он не является общедоступным
    private AppLogger() {}

    public static void init() {
        // Если это отладка, то добавляем новое дерево ведения журнала
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } // if
    }

    // Object... - возможность принимать не только объекты, но и массивы
    public static void d(String s, Object... objects) { Timber.d(s, objects); }
    public static void d(Throwable throwable, String s, Object... objects) {
        Timber.d(throwable, s, objects);
    }

    public static void i(String s, Object... objects) { Timber.i(s, objects); }
    public static void i(Throwable throwable, String s, Object... objects) {
        Timber.i(throwable, s, objects);
    }

    public static void w(String s, Object... objects) { Timber.w(s, objects); }
    public static void w(Throwable throwable, String s, Object... objects) {
        Timber.w(throwable, s, objects);
    }

    public static void e(String s, Object... objects) { Timber.e(s, objects); }
    public static void e(Throwable throwable, String s, Object... objects) {
        Timber.e(throwable, s, objects);
    }
}