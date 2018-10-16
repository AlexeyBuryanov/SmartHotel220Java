package com.alexeyburyanov.smarthotel.data.models.items;

/**
 * Created by Alexey on 24.03.2018.
 * Элемент списка в активности предложений.
 */
public class SuggestionItem {

    private int _picture;
    private String _name;
    private String _description;
    private float _rating;
    private int _numRating;

    public SuggestionItem(int picture, String name, String description, float rating, int numRating) {
        _picture = picture;
        _name = name;
        _description = description;
        _rating = rating;
        _numRating = numRating;
    }

    public int get_picture() {
        return _picture;
    }
    public void set_picture(int picture) {
        _picture = picture;
    }

    public String get_name() {
        return _name;
    }
    public void set_name(String name) {
        _name = name;
    }

    public String get_description() {
        return _description;
    }
    public void set_description(String description) {
        _description = description;
    }

    public float get_rating() {
        return _rating;
    }
    public void set_rating(float rating) {
        _rating = rating;
    }

    public int get_numRating() {
        return _numRating;
    }
    public void set_numRating(int numRating) {
        _numRating = numRating;
    }
}
