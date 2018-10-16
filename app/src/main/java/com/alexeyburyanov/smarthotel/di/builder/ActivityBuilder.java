package com.alexeyburyanov.smarthotel.di.builder;

import com.alexeyburyanov.smarthotel.ui.about.AboutFragmentProvider;
import com.alexeyburyanov.smarthotel.ui.booking.calendar.BookingCalendarActivity;
import com.alexeyburyanov.smarthotel.ui.booking.calendar.BookingCalendarActivityModule;
import com.alexeyburyanov.smarthotel.ui.booking.hotel.BookingHotelActivity;
import com.alexeyburyanov.smarthotel.ui.booking.hotel.BookingHotelActivityModule;
import com.alexeyburyanov.smarthotel.ui.booking.hotel.reviews.ReviewsFragmentProvider;
import com.alexeyburyanov.smarthotel.ui.booking.hotel.rooms.RoomsFragmentProvider;
import com.alexeyburyanov.smarthotel.ui.booking.hotel.thehotel.TheHotelFragmentProvider;
import com.alexeyburyanov.smarthotel.ui.booking.hotels.BookingHotelsActivity;
import com.alexeyburyanov.smarthotel.ui.booking.hotels.BookingHotelsActivityModule;
import com.alexeyburyanov.smarthotel.ui.booking.search.BookingSearchActivity;
import com.alexeyburyanov.smarthotel.ui.booking.search.BookingSearchActivityModule;
import com.alexeyburyanov.smarthotel.ui.login.LoginActivity;
import com.alexeyburyanov.smarthotel.ui.login.LoginActivityModule;
import com.alexeyburyanov.smarthotel.ui.main.MainActivity;
import com.alexeyburyanov.smarthotel.ui.main.MainActivityModule;
import com.alexeyburyanov.smarthotel.ui.myroom.MyRoomActivity;
import com.alexeyburyanov.smarthotel.ui.myroom.MyRoomActivityModule;
import com.alexeyburyanov.smarthotel.ui.myroom.ambientset.AmbientSetFragmentProvider;
import com.alexeyburyanov.smarthotel.ui.myroom.findmyroom.FindMyRoomFragmentProvider;
import com.alexeyburyanov.smarthotel.ui.myroom.whatyouneed.WhatYouNeedFragmentProvider;
import com.alexeyburyanov.smarthotel.ui.notifications.NotificationsActivity;
import com.alexeyburyanov.smarthotel.ui.notifications.NotificationsActivityModule;
import com.alexeyburyanov.smarthotel.ui.splash.SplashActivity;
import com.alexeyburyanov.smarthotel.ui.splash.SplashActivityModule;
import com.alexeyburyanov.smarthotel.ui.suggestions.SuggestionsActivity;
import com.alexeyburyanov.smarthotel.ui.suggestions.SuggestionsActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexey Buryanov on 23.02.2018
 * Основной модуль, который берёт на себя обязанность регистрации сабкомпонентов и модулей к ним.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = { MainActivityModule.class, AboutFragmentProvider.class })
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = BookingSearchActivityModule.class)
    abstract BookingSearchActivity bindBookingSearchActivity();

    @ContributesAndroidInjector(modules = BookingCalendarActivityModule.class)
    abstract BookingCalendarActivity bindBookingCalendarActivity();

    @ContributesAndroidInjector(modules = BookingHotelsActivityModule.class)
    abstract BookingHotelsActivity bindBookingHotelsActivity();

    @ContributesAndroidInjector(modules = { BookingHotelActivityModule.class, TheHotelFragmentProvider.class, RoomsFragmentProvider.class, ReviewsFragmentProvider.class })
    abstract BookingHotelActivity bindBookingHotelActivity();

    @ContributesAndroidInjector(modules = NotificationsActivityModule.class)
    abstract NotificationsActivity bindNotificationsActivity();

    @ContributesAndroidInjector(modules = { MyRoomActivityModule.class, AmbientSetFragmentProvider.class, FindMyRoomFragmentProvider.class, WhatYouNeedFragmentProvider.class })
    abstract MyRoomActivity bindMyRoomActivity();

    @ContributesAndroidInjector(modules = SuggestionsActivityModule.class)
    abstract SuggestionsActivity bindSuggestionsActivity();
}