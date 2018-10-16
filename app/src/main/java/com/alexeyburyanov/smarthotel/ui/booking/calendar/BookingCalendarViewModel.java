package com.alexeyburyanov.smarthotel.ui.booking.calendar;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey on 18.03.2018.
 */
public class BookingCalendarViewModel extends BaseViewModel<BookingCalendarNavigator> {

    public BookingCalendarViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNext() {
        new android.os.Handler().post(() -> {
            setIsLoading(true);
            getNavigator().openBookingHotelsActivity();
            setIsLoading(false);
        });
    }
}