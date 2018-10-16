package com.alexeyburyanov.smarthotel.ui.about;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
@Module
public abstract class AboutFragmentProvider {

    @ContributesAndroidInjector(modules = AboutFragmentModule.class)
    abstract AboutFragment provideAboutFragmentFactory();
}