package com.alexeyburyanov.smarthotel.ui.booking.hotel;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey on 21.03.2018.
 */
@Module
public class BookingHotelActivityModule {

    @Provides
    BookingHotelViewModel provideBookingHotelViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new BookingHotelViewModel(dataManager, schedulerProvider);
    }

    @Provides
    BookingHotelPagerAdapter provideBookingHotelPagerAdapter(BookingHotelActivity activity) {
        return new BookingHotelPagerAdapter(activity.getSupportFragmentManager());
    }
}