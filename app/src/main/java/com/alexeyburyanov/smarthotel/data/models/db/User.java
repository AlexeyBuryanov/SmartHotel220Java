package com.alexeyburyanov.smarthotel.data.models.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Alexey Buryanov 23.02.2018
 * Модель описывает структуру таблицы базы данных users.
 */
@Entity(tableName = "users")
public class User {

    @PrimaryKey public Long id;

    public String name;

    @ColumnInfo(name = "created_at")
    public String createdAt;

    @ColumnInfo(name = "updated_at")
    public String updatedAt;

    public User(Long id, String name, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}