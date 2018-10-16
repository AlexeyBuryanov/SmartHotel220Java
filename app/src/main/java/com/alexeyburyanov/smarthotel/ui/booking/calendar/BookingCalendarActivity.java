package com.alexeyburyanov.smarthotel.ui.booking.calendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.databinding.ActivityBookingCalendarBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;
import com.alexeyburyanov.smarthotel.ui.booking.hotels.BookingHotelsActivity;
import com.savvi.rangedatepicker.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by Alexey on 18.03.2018.
 */
public class BookingCalendarActivity extends BaseActivity<ActivityBookingCalendarBinding, BookingCalendarViewModel>
        implements BookingCalendarNavigator {

    @Inject BookingCalendarViewModel _bookingViewModel;
    ActivityBookingCalendarBinding _activityBookingBinding;

    private final static String CITY_NAME = "CITY_NAME";
    private Calendar _minDateC = Calendar.getInstance();
    private Calendar _maxDateC = Calendar.getInstance();
    private List<Date> _selectedDates = new LinkedList<>();
    private Date _minDate = new Date();
    private Date _maxDate = new Date();
    private SimpleDateFormat _simpleDateFormat = new SimpleDateFormat("EE, dd MMMM", new Locale("ru"));
    private String _fullCity;

    @NonNull
    public static Intent getStartIntent(Context context, String city) {
        Intent intent = new Intent(context, BookingCalendarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(CITY_NAME, city);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setUp();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _fullCity = extras.getString(CITY_NAME);
            if (_fullCity != null && _fullCity.length() != 0) {
                String subCity = _fullCity.substring(0, _fullCity.lastIndexOf(','));
                _activityBookingBinding.tvCity.setText(subCity);
            }
        }
    }

    private void setUp() {
        _bookingViewModel.setNavigator(this);
        _activityBookingBinding = getViewDataBinding();

        _minDateC.set(Calendar.YEAR, 2018);
        _minDateC.set(Calendar.MONTH, Calendar.MARCH);
        _minDateC.set(Calendar.DAY_OF_MONTH, 1);

        _maxDateC.set(Calendar.YEAR, 2018);
        _maxDateC.set(Calendar.MONTH, Calendar.JULY);
        _maxDateC.set(Calendar.DAY_OF_MONTH, 1);

        _activityBookingBinding.datePicker.init(_minDateC.getTime(), _maxDateC.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(new Date());

        _selectedDates = _activityBookingBinding.datePicker.getSelectedDates();
        _minDate = _selectedDates.get(0);
        _maxDate = _selectedDates.get(0);
        displayDates();

        _activityBookingBinding.datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                _selectedDates = _activityBookingBinding.datePicker.getSelectedDates();
                if (_selectedDates != null && _selectedDates.size() != 0) {
                    _minDate = _selectedDates.get(0);
                    _maxDate = _selectedDates.get(_selectedDates.size()-1);
                    displayDates();
                }
            }
            @Override
            public void onDateUnselected(Date date) {}
        });
    }

    private void displayDates() {
        String minDateS = _simpleDateFormat.format(_minDate);
        String maxDateS = _simpleDateFormat.format(_maxDate);
        _activityBookingBinding.etWhen.setText(String.format("%s - %s", minDateS, maxDateS));
    }

    @Override
    public void openBookingHotelsActivity() {
        startActivity(BookingHotelsActivity.getStartIntent(this, _fullCity,
                _minDate, _maxDate));
    }

    @Override
    public BookingCalendarViewModel getViewModel() { return _bookingViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_booking_calendar; }
}