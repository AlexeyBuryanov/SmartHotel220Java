package com.alexeyburyanov.smarthotel.ui.main;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public interface MainNavigator {

    void openLoginActivity();
    void openBookingActivity();
    void reOpenThis();
    void openMyRoomActivity();
    void openSuggestionsActivity();
    void openConciergeDialog();
    void handleError(Throwable throwable);
    MainActivity getMainActivity();
}