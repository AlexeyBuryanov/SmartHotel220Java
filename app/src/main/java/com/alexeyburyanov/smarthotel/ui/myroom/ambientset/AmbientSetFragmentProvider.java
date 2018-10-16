package com.alexeyburyanov.smarthotel.ui.myroom.ambientset;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
@Module
public abstract class AmbientSetFragmentProvider {

    @ContributesAndroidInjector(modules = AmbientSetFragmentModule.class)
    abstract AmbientSetFragment provideAmbientSetFactory();
}
