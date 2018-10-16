package com.alexeyburyanov.smarthotel.ui.booking.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.items.BookingSearchItem;
import com.alexeyburyanov.smarthotel.databinding.ActivityBookingSearchBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;
import com.alexeyburyanov.smarthotel.ui.booking.calendar.BookingCalendarActivity;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Alexey on 10.03.2018.
 */
public class BookingSearchActivity extends BaseActivity<ActivityBookingSearchBinding, BookingSearchViewModel>
        implements BookingSearchNavigator, BookingSearchAdapter.ItemClickListener {

    @Inject BookingSearchViewModel _bookingViewModel;
    @Inject BookingSearchAdapter _adapter;
    @Inject LinearLayoutManager _layoutManager;
    ActivityBookingSearchBinding _activityBookingBinding;

    private String _city;

    @NonNull
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, BookingSearchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        _bookingViewModel.setNavigator(this);
        _activityBookingBinding = getViewDataBinding();

        _adapter.getData().add(new BookingSearchItem("Сиэтл, США"));
        _adapter.getData().add(new BookingSearchItem("Барселона, Испания"));
        _adapter.getData().add(new BookingSearchItem("Киев, Украина"));
        _adapter.setClickListener(this);

        _layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _activityBookingBinding.recyclerViewWhere.setLayoutManager(_layoutManager);
        _activityBookingBinding.recyclerViewWhere.setItemAnimator(new DefaultItemAnimator());
        _activityBookingBinding.recyclerViewWhere.setAdapter(_adapter);

        _activityBookingBinding.etWhere.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                _adapter.getFilter().filter(s);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        _activityBookingBinding.buttonNext.setVisibility(View.VISIBLE);
        _city = _adapter.getItem(position).get_where();
        hideKeyboard();
    }

    @Override
    public void openBookingCalendarActivity() {
        startActivity(BookingCalendarActivity.getStartIntent(this, _city));
    }

    @Override
    public BookingSearchViewModel getViewModel() { return _bookingViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_booking_search; }
}