package com.alexeyburyanov.smarthotel.ui.booking.hotel.rooms;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexey on 23.03.2018.
 */
@Module
public abstract class RoomsFragmentProvider {

    @ContributesAndroidInjector(modules = RoomsFragmentModule.class)
    abstract RoomsFragment provideRoomsFactory();
}