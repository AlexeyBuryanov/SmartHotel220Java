package com.alexeyburyanov.smarthotel.data.local.db;

import com.alexeyburyanov.smarthotel.data.models.db.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alexey Buryanov on 23.02.2018
 * Интерфейс для описания всех методов по работе с базой.
 */
public interface DbHelper {

    Observable<Boolean> insertUser(User user);
    Observable<Boolean> findUser(String name);
    Observable<List<User>> getAllUsers();
}