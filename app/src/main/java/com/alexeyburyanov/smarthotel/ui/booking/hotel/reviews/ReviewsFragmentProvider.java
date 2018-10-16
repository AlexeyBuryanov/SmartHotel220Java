package com.alexeyburyanov.smarthotel.ui.booking.hotel.reviews;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexey on 23.03.2018.
 */
@Module
public abstract class ReviewsFragmentProvider {

    @ContributesAndroidInjector(modules = ReviewsFragmentModule.class)
    abstract ReviewsFragment provideReviewsFragmentFactory();
}