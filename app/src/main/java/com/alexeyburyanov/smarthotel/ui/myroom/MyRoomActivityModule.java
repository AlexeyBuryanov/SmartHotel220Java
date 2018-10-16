package com.alexeyburyanov.smarthotel.ui.myroom;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Buryanov 02.04.2018.
 */
@Module
public class MyRoomActivityModule {

    @Provides
    MyRoomViewModel provideMyRoomViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MyRoomViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MyRoomPagerAdapter provideMyRoomPagerAdapter(MyRoomActivity activity) {
        return new MyRoomPagerAdapter(activity.getSupportFragmentManager());
    }
}
