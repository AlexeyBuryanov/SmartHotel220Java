package com.alexeyburyanov.smarthotel.ui.myroom.findmyroom;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
@Module
public abstract class FindMyRoomFragmentProvider {

    @ContributesAndroidInjector(modules = FindMyRoomFragmentModule.class)
    abstract FindMyRoomFragment provideFindMyRoomFactory();
}
