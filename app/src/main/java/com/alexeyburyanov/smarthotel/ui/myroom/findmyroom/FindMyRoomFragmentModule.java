package com.alexeyburyanov.smarthotel.ui.myroom.findmyroom;

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
public class FindMyRoomFragmentModule {

    @Provides
    FindMyRoomViewModel findMyRoomViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new FindMyRoomViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideFindMyRoomViewModel(FindMyRoomViewModel findMyRoomViewModel) {
        return new ViewModelProviderFactory<>(findMyRoomViewModel);
    }
}
