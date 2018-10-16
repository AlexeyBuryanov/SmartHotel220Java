package com.alexeyburyanov.smarthotel.ui.myroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.databinding.ActivityMyroomBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;
import com.alexeyburyanov.smarthotel.ui.booking.search.BookingSearchActivity;
import com.alexeyburyanov.smarthotel.ui.main.MainActivity;
import com.alexeyburyanov.smarthotel.utils.ViewUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Alexey Buryanov 02.04.2018.
 */
public class MyRoomActivity extends BaseActivity<ActivityMyroomBinding, MyRoomViewModel>
        implements MyRoomNavigator, MyRoomCheckOutDialogData, HasSupportFragmentInjector {

    @Inject MyRoomViewModel _myRoomViewModel;
    @Inject MyRoomPagerAdapter _pagerAdapter;
    @Inject DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    ActivityMyroomBinding _activityMyRoomBinding;

    @NonNull
    public static Intent getStartIntent(Context context) {
        return new Intent(context, MyRoomActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activityMyRoomBinding = getViewDataBinding();
        _myRoomViewModel.setNavigator(this);
        setUp();
    }

    private void setUp() {
        _activityMyRoomBinding.btnCheckOut.setOnClickListener(v -> showCheckOutDialog());
        _activityMyRoomBinding.tvCheckOut.setOnClickListener(v -> showCheckOutDialog());

        _pagerAdapter.setCount(3);
        _activityMyRoomBinding.roomViewPager.setAdapter(_pagerAdapter);
        _activityMyRoomBinding.tabLayout.addTab(_activityMyRoomBinding.tabLayout.newTab().setText("Окружающие настройки"));
        _activityMyRoomBinding.tabLayout.addTab(_activityMyRoomBinding.tabLayout.newTab().setText("Что Вам нужно"));
        _activityMyRoomBinding.tabLayout.addTab(_activityMyRoomBinding.tabLayout.newTab().setText("Найти мой номер"));
        _activityMyRoomBinding.roomViewPager.setOffscreenPageLimit(_activityMyRoomBinding.tabLayout.getTabCount());
        _activityMyRoomBinding.roomViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(_activityMyRoomBinding.tabLayout));
        _activityMyRoomBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                _activityMyRoomBinding.roomViewPager.requestLayout();
                _activityMyRoomBinding.roomViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void showCheckOutDialog() {
        MyRoomCheckOutDialogFragment.newInstance(getViewModel().getDataManager().getCurrentUserName())
                .show(getSupportFragmentManager(), MyRoomCheckOutDialogFragment.TAG);
    }

    @Override
    public void goBack() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (upIntent != null) {
            NavUtils.navigateUpTo(this, upIntent);
        }
    }

    @Override
    public MyRoomActivity getMyRoomActivity() { return this; }

    @Override
    public void onBookingClick() {
        getViewModel().getDataManager().setBooking(false);
        startActivity(BookingSearchActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void onCheckOutClick() {
        getViewModel().getDataManager().setBooking(false);
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }

    @Override
    public MyRoomViewModel getViewModel() { return _myRoomViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_myroom; }
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
