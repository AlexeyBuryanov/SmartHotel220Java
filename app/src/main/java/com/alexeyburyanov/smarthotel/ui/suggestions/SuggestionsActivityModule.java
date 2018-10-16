package com.alexeyburyanov.smarthotel.ui.suggestions;

import android.support.v7.widget.LinearLayoutManager;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexey Buryanov 08.04.2018.
 */
@Module
public class SuggestionsActivityModule {

    @Provides
    SuggestionsViewModel provideSuggestionsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new SuggestionsViewModel(dataManager, schedulerProvider);
    }

    @Provides
    SuggestionsAdapter provideSuggestionsAdapter(SuggestionsActivity activity) {
        return new SuggestionsAdapter(activity, new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(SuggestionsActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
