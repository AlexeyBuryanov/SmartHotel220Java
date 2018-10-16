package com.alexeyburyanov.smarthotel.data.models.items;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alexey on 24.03.2018.
 * Элемент ревью(одного обзора) на отель.
 */
public class ReviewsItem implements Serializable {

    private String _reviewer;
    private String _typeRoom;
    private Date _date;
    private float _rating;
    private String _review;

    public ReviewsItem(String reviewer, String typeRoom, Date date, float rating, String review) {
        _reviewer = reviewer;
        _typeRoom = typeRoom;
        _date = date;
        _rating = rating;
        _review = review;
    }

    public String get_reviewer() {
        return _reviewer;
    }
    public void set_reviewer(String reviewer) {
        _reviewer = reviewer;
    }

    public String get_typeRoom() {
        return _typeRoom;
    }
    public void set_typeRoom(String typeRoom) {
        _typeRoom = typeRoom;
    }

    public Date get_date() {
        return _date;
    }
    public void set_date(Date date) {
        _date = date;
    }

    public float get_rating() {
        return _rating;
    }
    public void set_rating(float rating) {
        _rating = rating;
    }

    public String get_review() {
        return _review;
    }
    public void set_review(String review) {
        _review = review;
    }
}