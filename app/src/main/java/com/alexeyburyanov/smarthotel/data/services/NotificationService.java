package com.alexeyburyanov.smarthotel.data.services;

import android.databinding.ObservableArrayList;

import com.alexeyburyanov.smarthotel.data.models.Notification;
import com.alexeyburyanov.smarthotel.data.models.NotificationType;

/**
 * Created by Alexey on 28.02.2018.
 * Служба уведомлений. Здесь проходят все операции с уведомлениями.
 */
public class NotificationService implements INotificationService {

    @Override
    public ObservableArrayList<Notification> getNotifications(String token) {
        ObservableArrayList<Notification> data = new ObservableArrayList<>();
        data.add(new Notification("Службы по уборке закончили освежать комнату.", NotificationType.BeGreen));
        data.add(new Notification("Ваше обращение о вызове сантехников было подтверждено в 5:30 утра.", NotificationType.Room));
        data.add(new Notification("Ваш конференц-зал готов по вашему онлайн-запросу.", NotificationType.Hotel));
        data.add(new Notification("Бар SmartHotel220 с 8 вечера имеет ограничение только для 3 гостей.", NotificationType.Other));
        return data;
    }

    @Override
    public void deleteNotification(Notification notification) {

    }
}