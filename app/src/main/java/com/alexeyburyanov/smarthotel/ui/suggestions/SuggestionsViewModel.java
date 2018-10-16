package com.alexeyburyanov.smarthotel.ui.suggestions;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey Buryanov 08.04.2018.
 */
public class SuggestionsViewModel extends BaseViewModel<SuggestionsNavigator> {

    public SuggestionsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
