package com.alexeyburyanov.smarthotel.ui.myroom.whatyouneed;

import android.arch.lifecycle.ViewModelProvider;

import com.alexeyburyanov.smarthotel.ViewModelProviderFactory;
import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
@Module
public class WhatYouNeedFragmentModule {

    @Provides
    WhatYouNeedViewModel whatYouNeedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new WhatYouNeedViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideFindMyRoomViewModel(WhatYouNeedViewModel whatYouNeedViewModel) {
        return new ViewModelProviderFactory<>(whatYouNeedViewModel);
    }
}
