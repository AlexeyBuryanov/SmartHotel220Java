package com.alexeyburyanov.smarthotel.data.models.items;

import java.io.Serializable;

/**
 * Created by Alexey on 20.03.2018.
 * Элемент списка активности BookingHotels. Или другими словами один элемент из списка отелей.
 */
public class BookingHotelsItem implements Serializable {

    private int _picture;
    private String _fullCity;
    private String _nameHotel;
    private String _price;
    private float _rating;

    public BookingHotelsItem(int picture, String fullCity, String nameHotel, String price, int rating) {
        _picture = picture;
        _fullCity = fullCity;
        _nameHotel = nameHotel;
        _price = price;
        _rating = rating;
    }

    public int get_picture() {
        return _picture;
    }
    public void set_picture(int picture) {
        _picture = picture;
    }

    public String get_fullCity() {
        return _fullCity;
    }
    public void set_fullCity(String fullCity) {
        _fullCity = fullCity;
    }

    public String get_nameHotel() {
        return _nameHotel;
    }
    public void set_nameHotel(String nameHotel) {
        _nameHotel = nameHotel;
    }

    public String get_price() {
        return _price;
    }
    public void set_price(String price) {
        _price = price;
    }

    public float get_rating() {
        return _rating;
    }
    public void set_rating(float rating) {
        _rating = rating;
    }
}