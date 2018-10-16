package com.alexeyburyanov.smarthotel.ui.booking.search;

import android.support.v7.widget.LinearLayoutManager;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey on 10.03.2018.
 */
@Module
public class BookingSearchActivityModule {

    @Provides
    BookingSearchViewModel provideBookingSearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new BookingSearchViewModel(dataManager, schedulerProvider);
    }

    @Provides
    BookingSearchAdapter provideBookingSearchAdapter(BookingSearchActivity activity) {
        return new BookingSearchAdapter(activity, new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(BookingSearchActivity activity) {
        return new LinearLayoutManager(activity);
    }
}