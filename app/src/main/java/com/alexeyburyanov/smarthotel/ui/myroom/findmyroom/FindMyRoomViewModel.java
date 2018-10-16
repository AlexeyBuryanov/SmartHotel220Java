package com.alexeyburyanov.smarthotel.ui.myroom.findmyroom;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
public class FindMyRoomViewModel extends BaseViewModel<FindMyRoomNavigator> {

    public FindMyRoomViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
