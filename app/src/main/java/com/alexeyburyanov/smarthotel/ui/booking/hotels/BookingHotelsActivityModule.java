package com.alexeyburyanov.smarthotel.ui.booking.hotels;

import android.support.v7.widget.LinearLayoutManager;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey on 19.03.2018.
 */
@Module
public class BookingHotelsActivityModule {

    @Provides
    BookingHotelsViewModel provideBookingHotelsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new BookingHotelsViewModel(dataManager, schedulerProvider);
    }

    @Provides
    BookingHotelsAdapter provideBookingHotelsAdapter(BookingHotelsActivity activity) {
        return new BookingHotelsAdapter(activity, new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(BookingHotelsActivity activity) {
        return new LinearLayoutManager(activity);
    }
}