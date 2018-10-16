package com.alexeyburyanov.smarthotel.ui.booking.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.items.BookingHotelsItem;
import com.alexeyburyanov.smarthotel.data.models.items.ReviewsItem;
import com.alexeyburyanov.smarthotel.databinding.ActivityBookingHotelBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;
import com.alexeyburyanov.smarthotel.ui.main.MainActivity;
import com.alexeyburyanov.smarthotel.utils.ViewUtils;

import java.util.Date;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Alexey on 21.03.2018.
 */
public class BookingHotelActivity extends BaseActivity<ActivityBookingHotelBinding, BookingHotelViewModel>
        implements BookingHotelNavigator, BookingHotelDialogData, HasSupportFragmentInjector {

    @Inject BookingHotelViewModel _bookingViewModel;
    @Inject BookingHotelPagerAdapter _pagerAdapter;
    @Inject DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    ActivityBookingHotelBinding _activityBookingBinding;

    private final static String BOOKING_HOTEL = "BOOKING_HOTEL";
    private float _ratingReview = 2;
    private BookingHotelsItem _selectedItem;

    @NonNull
    public static Intent getStartIntent(Context context, BookingHotelsItem selectedItem) {
        Intent intent = new Intent(context, BookingHotelActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(BOOKING_HOTEL, selectedItem);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        _bookingViewModel.setNavigator(this);
        _activityBookingBinding = getViewDataBinding();

        setUpExtras();

        _pagerAdapter.setCount(3);
        _pagerAdapter.set_review(new ReviewsItem("Вася Пупкин",
                "Одноместный номер", new Date(), _ratingReview,
                "Очень хороший отель, но есть пара недочётов. В остальном рекомендую!"));
        _activityBookingBinding.hotelViewPager.setAdapter(_pagerAdapter);
        _activityBookingBinding.tabLayout.addTab(_activityBookingBinding.tabLayout.newTab().setText("Отель"));
        _activityBookingBinding.tabLayout.addTab(_activityBookingBinding.tabLayout.newTab().setText("Комнаты"));
        _activityBookingBinding.tabLayout.addTab(_activityBookingBinding.tabLayout.newTab().setText("Отзывы"));
        _activityBookingBinding.hotelViewPager.setOffscreenPageLimit(_activityBookingBinding.tabLayout.getTabCount());
        _activityBookingBinding.hotelViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(_activityBookingBinding.tabLayout));
        _activityBookingBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    _activityBookingBinding.hotelViewPager.getLayoutParams().height = ViewUtils.dpToPx(450);
                } else if (tab.getPosition() == 1) {
                    _activityBookingBinding.hotelViewPager.getLayoutParams().height = ViewUtils.dpToPx(700);
                } else {
                    _activityBookingBinding.hotelViewPager.getLayoutParams().height = ViewUtils.dpToPx(300);
                }
                _activityBookingBinding.hotelViewPager.requestLayout();
                _activityBookingBinding.hotelViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setUpExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _selectedItem = (BookingHotelsItem)extras.get(BOOKING_HOTEL);
            if (_selectedItem != null) {
                _activityBookingBinding.tvFullCity.setText(_selectedItem.get_fullCity());
                _activityBookingBinding.tvNameHotel.setText(_selectedItem.get_nameHotel());
                _activityBookingBinding.tvPrice.setText(_selectedItem.get_price());
                _activityBookingBinding.rbRatingHotel.setRating(_selectedItem.get_rating());
                _ratingReview = _selectedItem.get_rating();
                if (_selectedItem.get_fullCity().contains("Испания"))
                    _activityBookingBinding.ivImageHotel.setImageResource(R.mipmap.i_hotel_1);
                else if (_selectedItem.get_fullCity().contains("США")) {
                    _activityBookingBinding.ivImageHotel.setImageResource(R.mipmap.i_hotel_3);
                } else {
                    _activityBookingBinding.ivImageHotel.setImageResource(R.mipmap.i_hotel_2);
                }
            }
        }
    }

    @Override
    public void openMainActivity() {
        BookingHotelDialogFragment.newInstance(_activityBookingBinding.tvNameHotel.getText().toString())
                .show(getSupportFragmentManager(), BookingHotelDialogFragment.TAG);
    }

    @Override
    public void onPositiveClick() {
        getViewModel().getDataManager().setBooking(true);
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }

    @Override
    public BookingHotelViewModel getViewModel() { return _bookingViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_booking_hotel; }
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}