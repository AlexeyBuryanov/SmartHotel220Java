package com.alexeyburyanov.smarthotel.data;

import com.alexeyburyanov.smarthotel.data.local.db.DbHelper;
import com.alexeyburyanov.smarthotel.data.local.prefs.PreferencesHelper;
import com.alexeyburyanov.smarthotel.data.remote.ApiHelper;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * Интерфейс менеджера данных. Реализует интерфейсы ключевых узлов (компонентов/деталей) приложения.
 */
public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void updateApiHeader(Long userId, String accessToken);
    void setUserAsLoggedOut();
    void updateUserInfo(String accessToken, Long userId, LoggedInMode loggedInMode,
                        String userName, String email, String profilePicPath);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int _type;

        LoggedInMode(int type) { _type = type; }

        public int getType() { return _type; }
    } // enum
}