package com.alexeyburyanov.smarthotel.ui.booking.search;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey on 10.03.2018.
 */
public class BookingSearchViewModel extends BaseViewModel<BookingSearchNavigator> {

    public BookingSearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNext() {
        new android.os.Handler().post(() -> {
            setIsLoading(true);
            getNavigator().openBookingCalendarActivity();
            setIsLoading(false);
        });
    }
}