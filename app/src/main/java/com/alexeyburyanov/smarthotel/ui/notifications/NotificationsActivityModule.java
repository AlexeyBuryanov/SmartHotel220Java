package com.alexeyburyanov.smarthotel.ui.notifications;

import android.support.v7.widget.LinearLayoutManager;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Buryanov 29.02.2018.
 */
@Module
public class NotificationsActivityModule {

    @Provides
    NotificationsViewModel provideLoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new NotificationsViewModel(dataManager, schedulerProvider);
    }

    @Provides
    NotificationsAdapter provideNotificationsAdapter(NotificationsActivity activity) {
        return new NotificationsAdapter(activity, new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(NotificationsActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
