package com.alexeyburyanov.smarthotel.ui.booking.hotel.reviews;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.alexeyburyanov.smarthotel.ViewModelProviderFactory;
import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey on 23.03.2018.
 */
@Module
public class ReviewsFragmentModule {

    @Provides
    ReviewsViewModel reviewsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new ReviewsViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideReviewsViewModel(ReviewsViewModel reviewsViewModel) {
        return new ViewModelProviderFactory<>(reviewsViewModel);
    }

    @Provides
    ReviewsAdapter provideReviewsAdapter(ReviewsFragment fragment) {
        return new ReviewsAdapter(fragment.getActivity(), new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ReviewsFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}