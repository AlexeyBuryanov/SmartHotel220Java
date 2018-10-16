package com.alexeyburyanov.smarthotel;

import android.app.Activity;
import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.alexeyburyanov.smarthotel.di.component.DaggerAppComponent;
import com.alexeyburyanov.smarthotel.utils.AppLogger;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public class App extends Application implements HasActivityInjector {

    // @Inject - запрашивает зависимость
    @Inject CalligraphyConfig calligraphyConfig;
    // Содержит все фабрики создания сабкомпонентов для наследников Activity.
    // При инжекте ищет нужную фабрику, создает сабкомпонент(AndroidInject)
    // и инжектит зависимости.
    @Inject DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        // Внедрение зависимостей в приложение
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        // Включаем логгирование, если это отладка
        AppLogger.init();

        // Инициализация AndroidNetworking с конфигом по дефолту
        AndroidNetworking.initialize(getApplicationContext());
        // Если отладка, то включаем логгирование для AndroidNetworking
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        } // if

        // Устанавливаем кастомный шрифт используя CalligraphyConfig от Christopher Jenkins (chrisjenx)
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    } // activityInjector
}