package com.alexeyburyanov.smarthotel.ui.booking.hotel;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey on 21.03.2018.
 */
public class BookingHotelViewModel extends BaseViewModel<BookingHotelNavigator> {

    public BookingHotelViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBookingNow() {
        new android.os.Handler().post(() -> {
            setIsLoading(true);
            getNavigator().openMainActivity();
            setIsLoading(false);
        });
    }
}