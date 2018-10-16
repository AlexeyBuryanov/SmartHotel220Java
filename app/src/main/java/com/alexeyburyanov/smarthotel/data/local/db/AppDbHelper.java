package com.alexeyburyanov.smarthotel.data.local.db;

import com.alexeyburyanov.smarthotel.data.models.db.User;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Alexey Buryanov on 23.02.2018
 * Класс-помошник по работе с базой. Реализует интерфейс DbHelper.
 */
@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase _appDatabase;

    @Inject
    AppDbHelper(AppDatabase appDatabase) { _appDatabase = appDatabase; }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(() -> _appDatabase.userDao().loadAll());
    }

    @Override
    public Observable<Boolean> insertUser(User user) {
        return Observable.fromCallable(() -> {
            try {
                _appDatabase.userDao().insert(user);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    } // insertUser

    @Override
    public Observable<Boolean> findUser(String name) {
        return Observable.fromCallable(() -> {
            User user = _appDatabase.userDao().findByName(name);
            return user != null;
        });
    } // getUser
}