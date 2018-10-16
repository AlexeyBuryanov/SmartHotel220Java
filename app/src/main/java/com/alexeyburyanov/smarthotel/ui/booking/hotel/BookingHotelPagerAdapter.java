package com.alexeyburyanov.smarthotel.ui.booking.hotel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alexeyburyanov.smarthotel.ui.booking.hotel.reviews.ReviewsFragment;
import com.alexeyburyanov.smarthotel.data.models.items.ReviewsItem;
import com.alexeyburyanov.smarthotel.ui.booking.hotel.rooms.RoomsFragment;
import com.alexeyburyanov.smarthotel.ui.booking.hotel.thehotel.TheHotelFragment;

/**
 * Created by Alexey on 23.03.2018.
 */
public class BookingHotelPagerAdapter extends FragmentStatePagerAdapter {

    private int _tabCount;
    private ReviewsItem _review;

    BookingHotelPagerAdapter(FragmentManager fm) {
        super(fm);
        _tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TheHotelFragment.newInstance();
            case 1:
                return RoomsFragment.newInstance();
            case 2:
                return ReviewsFragment.newInstance(_review);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return _tabCount;
    }
    public void setCount(int count) {
        _tabCount = count;
    }

    public void set_review(ReviewsItem review) {
        _review = review;
    }
}