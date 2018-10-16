package com.alexeyburyanov.smarthotel.ui.myroom.ambientset;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
public class AmbientSetViewModel extends BaseViewModel<AmbientSetNavigator> {

    public AmbientSetViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
