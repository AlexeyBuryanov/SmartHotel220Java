package com.alexeyburyanov.smarthotel.ui.booking.hotel.rooms;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey on 23.03.2018.
 */
public class RoomsViewModel extends BaseViewModel<RoomsNavigator> {

    public RoomsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}