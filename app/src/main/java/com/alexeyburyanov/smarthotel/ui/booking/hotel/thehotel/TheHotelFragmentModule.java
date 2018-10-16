package com.alexeyburyanov.smarthotel.ui.booking.hotel.thehotel;

import android.arch.lifecycle.ViewModelProvider;

import com.alexeyburyanov.smarthotel.ViewModelProviderFactory;
import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey on 23.03.2018.
 */
@Module
public class TheHotelFragmentModule {

    @Provides
    TheHotelViewModel theHotelViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new TheHotelViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideTheHotelViewModel(TheHotelViewModel theHotelViewModel) {
        return new ViewModelProviderFactory<>(theHotelViewModel);
    }
}