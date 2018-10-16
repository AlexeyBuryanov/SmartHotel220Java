package com.alexeyburyanov.smarthotel.ui.booking.hotels;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey on 19.03.2018.
 */
public class BookingHotelsViewModel extends BaseViewModel<BookingHotelsNavigator> {

    public BookingHotelsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}