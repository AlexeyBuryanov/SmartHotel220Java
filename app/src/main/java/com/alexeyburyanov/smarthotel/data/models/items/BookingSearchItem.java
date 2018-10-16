package com.alexeyburyanov.smarthotel.data.models.items;

/**
 * Created by Alexey on 14.03.2018.
 * Элемент поиска по странею
 */
public class BookingSearchItem {

    private String _where;

    public BookingSearchItem(String where) {
        _where = where;
    }

    public String get_where() {
        return _where;
    }
    public void set_where(String where) {
        _where = where;
    }
}