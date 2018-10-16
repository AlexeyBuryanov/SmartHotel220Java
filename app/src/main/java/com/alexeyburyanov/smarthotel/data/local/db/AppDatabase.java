package com.alexeyburyanov.smarthotel.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alexeyburyanov.smarthotel.data.local.db.dao.UserDao;
import com.alexeyburyanov.smarthotel.data.models.db.User;

/**
 * Created by Alexey Buryanov on 23.02.2018
 * Описание структуры базы данных ROOM.
 */
@Database(entities = { User.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}