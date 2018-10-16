package com.alexeyburyanov.smarthotel.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.AppDataManager;
import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.data.local.db.AppDatabase;
import com.alexeyburyanov.smarthotel.data.local.db.AppDbHelper;
import com.alexeyburyanov.smarthotel.data.local.db.DbHelper;
import com.alexeyburyanov.smarthotel.data.local.prefs.AppPreferencesHelper;
import com.alexeyburyanov.smarthotel.data.local.prefs.PreferencesHelper;
import com.alexeyburyanov.smarthotel.data.remote.ApiHeader;
import com.alexeyburyanov.smarthotel.data.remote.ApiHelper;
import com.alexeyburyanov.smarthotel.data.remote.AppApiHelper;
import com.alexeyburyanov.smarthotel.di.ApiInfo;
import com.alexeyburyanov.smarthotel.di.DatabaseInfo;
import com.alexeyburyanov.smarthotel.di.PreferenceInfo;
import com.alexeyburyanov.smarthotel.utils.AppConstants;
import com.alexeyburyanov.smarthotel.utils.rx.AppSchedulerProvider;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) { return application; }

    @Provides
    SchedulerProvider provideSchedulerProvider() { return new AppSchedulerProvider(); }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() { return AppConstants.DB_NAME; }

    @Provides
    @ApiInfo
    String provideApiKey() { return "API_KEY"; }

    @Provides
    @PreferenceInfo
    String providePreferenceName() { return AppConstants.PREF_NAME; }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) { return appDataManager; }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) { return appDbHelper; }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) { return appPreferencesHelper; }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) { return appApiHelper; }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey, PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}