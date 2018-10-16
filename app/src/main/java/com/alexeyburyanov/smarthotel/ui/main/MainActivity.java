package com.alexeyburyanov.smarthotel.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.BuildConfig;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.Notification;
import com.alexeyburyanov.smarthotel.data.models.NotificationType;
import com.alexeyburyanov.smarthotel.data.services.INotificationService;
import com.alexeyburyanov.smarthotel.databinding.ActivityMainBinding;
import com.alexeyburyanov.smarthotel.databinding.NavHeaderMainBinding;
import com.alexeyburyanov.smarthotel.ui.about.AboutFragment;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;
import com.alexeyburyanov.smarthotel.ui.booking.search.BookingSearchActivity;
import com.alexeyburyanov.smarthotel.ui.concierge.ConciergeDialogFragment;
import com.alexeyburyanov.smarthotel.ui.custom.TemperatureView;
import com.alexeyburyanov.smarthotel.ui.login.LoginActivity;
import com.alexeyburyanov.smarthotel.ui.myroom.MyRoomActivity;
import com.alexeyburyanov.smarthotel.ui.notifications.NotificationsActivity;
import com.alexeyburyanov.smarthotel.ui.suggestions.SuggestionsActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements MainNavigator, HasSupportFragmentInjector {

    @Inject ViewModelProvider.Factory _viewModelFactory;
    @Inject DispatchingAndroidInjector<Fragment> _fragmentDispatchingAndroidInjector;
    @Inject INotificationService _notificationService;
    ActivityMainBinding _activityMainBinding;
    private MainViewModel _mainViewModel;

    private static final int REQUEST_ACCESS_TYPE = 1;
    private static final String ACCESS_LIST = "ACCESS_LIST";
    private ObservableArrayList<Notification> _notifications = new ObservableArrayList<>();
    private TemperatureView _temperatureView;
    private android.os.Handler _handler;

    @NonNull
    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ACCESS_TYPE){
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                ArrayList<Notification> list = extras.get(ACCESS_LIST) instanceof ArrayList
                        ? (ArrayList<Notification>)extras.get(ACCESS_LIST)
                        : new ArrayList<>();
                _notifications.clear();
                _notifications.addAll(list);
                setUpCarousel(false);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        //        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        _handler = new Handler(getMainLooper());
        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        unlockDrawer();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    private void setUp() {
        _mainViewModel.setNavigator(this);
        _activityMainBinding = getViewDataBinding();

        _temperatureView = _activityMainBinding.temperatureView;
        _temperatureView.startAnim(21, 3000);

        _activityMainBinding.ambientLightProgress.setScaleY(1.888f);
        _activityMainBinding.ambientLightProgress.setProgress(600);
        _activityMainBinding.tvAmbientLight.setText(String.format("%sK", String.valueOf(_activityMainBinding.ambientLightProgress.getProgress())));

        _activityMainBinding.tvSuggestions.setOnClickListener(v -> {
            _handler.post(this::openSuggestionsActivity);
        });
        _activityMainBinding.tvSuggestions2.setOnClickListener(v -> {
            _handler.post(this::openSuggestionsActivity);
        });
        _activityMainBinding.tvSeeAll.setOnClickListener(v -> {
            _handler.post(this::openSuggestionsActivity);
        });
        _activityMainBinding.tvSeeAll2.setOnClickListener(v -> {
            _handler.post(this::openSuggestionsActivity);
        });
        setUpCarousel(true);
        setUpToolbar();
        setUpNavMenu();
    }

    private void setUpToolbar() {
        _activityMainBinding.toolbar.setTitle("");
        setSupportActionBar(_activityMainBinding.toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, _activityMainBinding.drawerView,
                _activityMainBinding.toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        _activityMainBinding.drawerView.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void setUpCarousel(boolean isStart) {
        if (isStart) {
            _notifications = _notificationService.getNotifications("token");
        }

        _activityMainBinding.carouselView.setPageCount(_notifications.size());
        _activityMainBinding.carouselView.setViewListener(position -> {
            View customView = getLayoutInflater().inflate(R.layout.item_carouselview, null);
            Notification notification = _notifications.get(position);

            TextView type = customView.findViewById(R.id.tvType);
            NotificationType nt = notification.get_type();

            ImageView ivBackground = customView.findViewById(R.id.ivBackground);
            ivBackground.setImageResource(R.mipmap.hero_image);

            String nts = "";
            int icRes = 0;
            switch (nt) {
                case Room:
                    nts = "Номер";
                    icRes = R.mipmap.ic_room;
                    break;
                case Hotel:
                    nts = "Отель";
                    icRes = R.mipmap.ic_hotel;
                    break;
                case BeGreen:
                    nts = "Уборка";
                    icRes = R.mipmap.ic_be_green;
                    break;
                case Other:
                    nts = "Другое";
                    icRes = R.mipmap.ic_other;
                    break;
            } // switch
            type.setText(nts);

            TextView text = customView.findViewById(R.id.tvText);
            text.setText(notification.get_text());

            ImageView ivIcon = customView.findViewById(R.id.ivIcon);
            ivIcon.setImageResource(icRes);

            return customView;
        });
        _activityMainBinding.carouselView.setImageClickListener(v -> {
            _handler.post(() -> {
               _mainViewModel.setIsLoading(true);
               startActivityForResult(NotificationsActivity.getStartIntent(this, _notifications), REQUEST_ACCESS_TYPE);
               _mainViewModel.setIsLoading(false);
           });
       });
    }

    private void setUpNavMenu() {
        _mainViewModel.onNavMenuCreated();
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header_main, _activityMainBinding.navigationView, false);
        _activityMainBinding.navigationView.addHeaderView(navHeaderMainBinding.getRoot());
        navHeaderMainBinding.setViewModel(_mainViewModel);

        Menu menu = _activityMainBinding.navigationView.getMenu();
        MenuItem myRoom = menu.findItem(R.id.navItemMyRoom);
        if (getViewModel().getIsBooking().get()) {
            myRoom.setEnabled(true);
        }

        _activityMainBinding.navigationView.setNavigationItemSelectedListener(item -> {
            _activityMainBinding.drawerView.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.navItemAbout:
                    _handler.post(this::showAboutFragment);
                    return true;
                case R.id.navItemBookRoom:
                    _handler.post(this::openBookingActivity);
                    return true;
                case R.id.navItemMyRoom:
                    _handler.post(this::openMyRoomActivity);
                    return true;
                case R.id.navItemSuggestions:
                    _handler.post(this::openSuggestionsActivity);
                    return true;
                case R.id.navItemHome:
                    _handler.post(this::reOpenThis);
                    return true;
                case R.id.navItemConcierge:
                    _handler.post(this::openConciergeDialog);
                    return true;
                case R.id.navItemLogout:
                    _handler.post(() -> _mainViewModel.logout());
                    return true;
                default:
                    return false;
            } // switch
        });

        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        _mainViewModel.updateAppVersion(version);
    }

    private void showAboutFragment() {
        lockDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }

    private void lockDrawer() { _activityMainBinding.drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); }
    private void unlockDrawer() { _activityMainBinding.drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void openBookingActivity() { startActivity(BookingSearchActivity.getStartIntent(this)); }

    @Override
    public void reOpenThis() { setUpCarousel(true); }

    @Override
    public void openMyRoomActivity() { startActivity(MyRoomActivity.getStartIntent(this)); }

    @Override
    public void openSuggestionsActivity() { startActivity(SuggestionsActivity.getStartIntent(this)); }

    @Override
    public void openConciergeDialog() { ConciergeDialogFragment.newInstance()
            .show(getSupportFragmentManager(), ConciergeDialogFragment.TAG);
    }

    @Override
    public void handleError(Throwable throwable) {}

    @Override
    public MainActivity getMainActivity() { return this; }

    @Override
    public MainViewModel getViewModel() {
        _mainViewModel = ViewModelProviders.of(this, _viewModelFactory).get(MainViewModel.class);
        return _mainViewModel;
    }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_main; }
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return _fragmentDispatchingAndroidInjector;
    }
}