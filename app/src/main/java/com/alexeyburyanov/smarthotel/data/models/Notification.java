package com.alexeyburyanov.smarthotel.data.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Alexey on 28.02.2018.
 * Уведомление.
 */
public class Notification implements Serializable {

    private String _text;
    private Date _time;
    private NotificationType _type;

    public Notification() {
        _text = "";
        _time = Date.from(Instant.EPOCH);
        _type = NotificationType.Room;
    }

    public Notification(int seq, String text, Date time, NotificationType type) {
        _text = text;
        _time = time;
        _type = type;
    }
    public Notification(String text, NotificationType type) {
        _text = text;
        _time = new Date();
        _type = type;
    }

    public String get_text() { return _text; }
    public void set_text(String text) { _text = text; }

    public Date get_time() { return _time; }
    public void set_time(Date time) { _time = time; }

    public NotificationType get_type() { return _type; }
    public void set_type(NotificationType type) { _type = type; }
}