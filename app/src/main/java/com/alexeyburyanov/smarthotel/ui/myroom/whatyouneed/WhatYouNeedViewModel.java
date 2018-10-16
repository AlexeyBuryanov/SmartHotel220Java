package com.alexeyburyanov.smarthotel.ui.myroom.whatyouneed;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
public class WhatYouNeedViewModel extends BaseViewModel<WhatYouNeedNavigator> {

    public WhatYouNeedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
