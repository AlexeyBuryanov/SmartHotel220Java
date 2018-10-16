package com.alexeyburyanov.smarthotel.ui.myroom.ambientset;

import android.arch.lifecycle.ViewModelProvider;

import com.alexeyburyanov.smarthotel.ViewModelProviderFactory;
import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
@Module
public class AmbientSetFragmentModule {

    @Provides
    AmbientSetViewModel ambientSetViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new AmbientSetViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideAmbientSetViewModel(AmbientSetViewModel ambientSetViewModel) {
        return new ViewModelProviderFactory<>(ambientSetViewModel);
    }
}
