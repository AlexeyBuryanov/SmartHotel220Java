package com.alexeyburyanov.smarthotel.data.services;

import android.databinding.ObservableArrayList;

import com.alexeyburyanov.smarthotel.data.models.Notification;

/**
 * Created by Alexey on 28.02.2018.
 * Интерфейс сервиса уведомлений.
 */
public interface INotificationService {

    ObservableArrayList<Notification> getNotifications(String token);
    void deleteNotification(Notification notification);
}