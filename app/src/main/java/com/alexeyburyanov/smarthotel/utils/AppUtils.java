package com.alexeyburyanov.smarthotel.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.alexeyburyanov.smarthotel.R;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * Вспомогательные методы приложения.
 */
public final class AppUtils {

    private AppUtils() {
        // Этот класс не является общедоступным
    }

    //public static void openPlayStoreForApp(Context context) {
    //    final String appPackageName = context.getPackageName();
    //    try {
    //        context.startActivity(new Intent(Intent.ACTION_VIEW,
    //                Uri.parse(context
    //                        .getResources()
    //                        .getString(R.string.app_market_link) + appPackageName)));
    //    } catch (android.content.ActivityNotFoundException e) {
    //        context.startActivity(new Intent(Intent.ACTION_VIEW,
    //                Uri.parse(context
    //                        .getResources()
    //                        .getString(R.string.app_google_play_store_link) + appPackageName)));
    //    }
    //}
}