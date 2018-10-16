package com.alexeyburyanov.smarthotel.ui.notifications;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey Buryanov 29.02.2018.
 */
public class NotificationsViewModel extends BaseViewModel<NotificationsNavigator> {

    public NotificationsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }
}