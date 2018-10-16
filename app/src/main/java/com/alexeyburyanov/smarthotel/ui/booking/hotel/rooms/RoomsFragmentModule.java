package com.alexeyburyanov.smarthotel.ui.booking.hotel.rooms;

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
public class RoomsFragmentModule {

    @Provides
    RoomsViewModel roomsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new RoomsViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideRoomsViewModel(RoomsViewModel roomsViewModel) {
        return new ViewModelProviderFactory<>(roomsViewModel);
    }
}