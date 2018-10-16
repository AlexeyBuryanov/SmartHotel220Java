package com.alexeyburyanov.smarthotel.ui.myroom.whatyouneed;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
@Module
public abstract class WhatYouNeedFragmentProvider {

    @ContributesAndroidInjector(modules = WhatYouNeedFragmentModule.class)
    abstract WhatYouNeedFragment provideWhatYouNeedFactory();
}