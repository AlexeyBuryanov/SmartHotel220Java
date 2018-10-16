package com.alexeyburyanov.smarthotel.ui.booking.hotel.thehotel;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey on 23.03.2018.
 */
public class TheHotelViewModel extends BaseViewModel<TheHotelNavigator> {

    public TheHotelViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}