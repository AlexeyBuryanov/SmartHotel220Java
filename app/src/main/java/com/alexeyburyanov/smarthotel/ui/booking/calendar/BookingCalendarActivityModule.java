package com.alexeyburyanov.smarthotel.ui.booking.calendar;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey on 18.03.2018.
 */
@Module
public class BookingCalendarActivityModule {

    @Provides
    BookingCalendarViewModel provideBookingCelandarViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new BookingCalendarViewModel(dataManager, schedulerProvider);
    }
}