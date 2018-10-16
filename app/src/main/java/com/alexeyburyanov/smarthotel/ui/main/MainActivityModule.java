package com.alexeyburyanov.smarthotel.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.alexeyburyanov.smarthotel.ViewModelProviderFactory;
import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.data.services.INotificationService;
import com.alexeyburyanov.smarthotel.data.services.NotificationService;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MainViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    INotificationService provideNotificationService() {
        return new NotificationService();
    }
}