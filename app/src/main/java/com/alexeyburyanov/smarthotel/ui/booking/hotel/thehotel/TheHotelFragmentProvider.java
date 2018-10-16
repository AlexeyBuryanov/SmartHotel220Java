package com.alexeyburyanov.smarthotel.ui.booking.hotel.thehotel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexey on 23.03.2018.
 */
@Module
public abstract class TheHotelFragmentProvider {

    @ContributesAndroidInjector(modules = TheHotelFragmentModule.class)
    abstract TheHotelFragment provideTheHotelFragmentFactory();
}