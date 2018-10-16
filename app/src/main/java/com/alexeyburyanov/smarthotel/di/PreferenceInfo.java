package com.alexeyburyanov.smarthotel.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Информация о настройках.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
    public @interface PreferenceInfo {
}