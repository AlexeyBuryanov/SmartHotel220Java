package com.alexeyburyanov.smarthotel.ui.booking.hotel.reviews;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey on 23.03.2018.
 */
public class ReviewsViewModel extends BaseViewModel<ReviewsNavigator> {

    public ReviewsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}