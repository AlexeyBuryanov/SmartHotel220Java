package com.alexeyburyanov.smarthotel.ui.booking.hotels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.items.BookingHotelsItem;
import com.alexeyburyanov.smarthotel.databinding.ActivityBookingHotelsBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;
import com.alexeyburyanov.smarthotel.ui.booking.hotel.BookingHotelActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by Alexey on 19.03.2018.
 */
public class BookingHotelsActivity extends BaseActivity<ActivityBookingHotelsBinding, BookingHotelsViewModel>
        implements BookingHotelsNavigator, BookingHotelsAdapter.ItemClickListener {

    @Inject BookingHotelsViewModel _bookingViewModel;
    @Inject BookingHotelsAdapter _adapter;
    @Inject LinearLayoutManager _layoutManager;
    ActivityBookingHotelsBinding _activityBookingBinding;

    private final static String CITY_NAME = "CITY_NAME";
    private final static String BOOKING_MINDATE = "BOOKING_MINDATE";
    private final static String BOOKING_MAXDATE = "BOOKING_MAXDATE";
    private SimpleDateFormat _simpleDateFormat = new SimpleDateFormat("EE, dd MMMM", new Locale("ru"));
    private Date _minDate = new Date();
    private Date _maxDate = new Date();
    private String _fullCity;
    private BookingHotelsItem _selectedItem;

    @NonNull
    public static Intent getStartIntent(Context context, String city, Date minDate, Date maxDate) {
        Intent intent = new Intent(context, BookingHotelsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(CITY_NAME, city);
        intent.putExtra(BOOKING_MINDATE, minDate);
        intent.putExtra(BOOKING_MAXDATE, maxDate);
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
        setUpRecyclerView();

        _activityBookingBinding.tvNumRooms.setText("1 номер");
    }

    private void setUpExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _fullCity = extras.getString(CITY_NAME);
            _minDate = (Date)extras.get(BOOKING_MINDATE);
            _maxDate = (Date)extras.get(BOOKING_MAXDATE);
            if (_fullCity != null && _fullCity.length() != 0) {
                String subCity = _fullCity.substring(0, _fullCity.lastIndexOf(','));
                _activityBookingBinding.tvCity.setText(subCity);
            }
            if (_minDate != null && _maxDate != null) {
                String minDateS = _simpleDateFormat.format(_minDate);
                String maxDateS = _simpleDateFormat.format(_maxDate);
                _activityBookingBinding.tvWhen.setText(String.format("%s - %s", minDateS, maxDateS));
            }
        }
    }

    private void setUpRecyclerView() {
        if (Objects.equals(_fullCity, "Барселона, Испания")) {
            _adapter.getData().add(new BookingHotelsItem(R.mipmap.img_1, _fullCity,
                    "Отель Тайный Лагерь", "$ 76 / ночь", 3));
        } else if (Objects.equals(_fullCity, "Сиэтл, США")) {
            _adapter.getData().add(new BookingHotelsItem(R.mipmap.img_3, _fullCity,
                    "Элитный Отель", "$ 220 / ночь", 4));
        } else {
            _adapter.getData().add(new BookingHotelsItem(R.mipmap.img_2, _fullCity,
                    "Отель Призма", "$ 161 / ночь", 3));
        }
        _adapter.setClickListener(this);
        _layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _activityBookingBinding.recyclerViewHotels.setLayoutManager(_layoutManager);
        _activityBookingBinding.recyclerViewHotels.setItemAnimator(new DefaultItemAnimator());
        _activityBookingBinding.recyclerViewHotels.setAdapter(_adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        _selectedItem = _adapter.getItem(position);
        showLoading();
        startActivity(BookingHotelActivity.getStartIntent(this, _selectedItem));
        new android.os.Handler(getMainLooper()).postDelayed(this::hideLoading, 100);
    }

    @Override
    public BookingHotelsViewModel getViewModel() { return _bookingViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_booking_hotels; }
}