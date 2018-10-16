package com.alexeyburyanov.smarthotel.ui.login;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
@Module
public class LoginActivityModule {

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new LoginViewModel(dataManager, schedulerProvider);
    }
}