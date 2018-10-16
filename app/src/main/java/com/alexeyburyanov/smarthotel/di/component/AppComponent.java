package com.alexeyburyanov.smarthotel.di.component;

import android.app.Application;

import com.alexeyburyanov.smarthotel.App;
import com.alexeyburyanov.smarthotel.di.builder.ActivityBuilder;
import com.alexeyburyanov.smarthotel.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Компонент приложения для построения зависимостей.
 */
@Singleton
@Component(modules = { AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class })
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}