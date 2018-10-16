package com.alexeyburyanov.smarthotel.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.Notification;
import com.alexeyburyanov.smarthotel.databinding.ActivityNotificationsBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by Alexey Buryanov 29.02.2018.
 */
public class NotificationsActivity extends BaseActivity<ActivityNotificationsBinding, NotificationsViewModel>
        implements NotificationsNavigator, NotificationsAdapter.ItemClickListener {

    @Inject NotificationsViewModel _notificationsViewModel;
    @Inject NotificationsAdapter _adapter;
    @Inject LinearLayoutManager _layoutManager;
    ActivityNotificationsBinding _activityNotifiBinding;

    private final static String GETS_NOTIFI = "GETS_NOTIFI";
    public static final String ACCESS_LIST = "ACCESS_LIST";
    private ArrayList<Notification> _notifications = new ArrayList<>();
    private Notification _selectedItem;

    @NonNull
    public static Intent getStartIntent(Context context, ObservableArrayList<Notification> notifications) {
        Intent intent = new Intent(context, NotificationsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(GETS_NOTIFI, notifications);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        _activityNotifiBinding = getViewDataBinding();
        _notificationsViewModel.setNavigator(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _notifications = extras.get(GETS_NOTIFI) instanceof ArrayList
                    ? (ArrayList<Notification>)extras.get(GETS_NOTIFI)
                    : new ArrayList<>();
        }

        _adapter.getData().addAll(Objects.requireNonNull(_notifications));
        _adapter.setClickListener(this);

        _layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _activityNotifiBinding.recyclerViewNotifi.setLayoutManager(_layoutManager);
        _activityNotifiBinding.recyclerViewNotifi.setItemAnimator(new DefaultItemAnimator());
        _activityNotifiBinding.recyclerViewNotifi.setAdapter(_adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        // TODO: Переделать без костыля с try-catch
        try {
            _selectedItem = _adapter.getItem(position);
            setTvHasNoItemsVisible();
        } catch (java.lang.IndexOutOfBoundsException e) {
            setTvHasNoItemsVisible();
        }
    }

    private void setTvHasNoItemsVisible() {
        if (_adapter.getData().size() == 0) {
            _activityNotifiBinding.tvHasNoItems.setVisibility(View.VISIBLE);
        } else {
            _activityNotifiBinding.tvHasNoItems.setVisibility(View.GONE);
        }
    }

    @Override
    public void goBack() {
        setReturnResult();
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (upIntent != null) {
            NavUtils.navigateUpTo(this, upIntent);
        }
    }

    @Override
    public void onBackPressed() {
        setReturnResult();
        super.onBackPressed();
    }

    private void setReturnResult() {
        Intent data = new Intent();
        if (_adapter.getData().size() == 0) {
            data.putExtra(ACCESS_LIST, new ArrayList<>());
        } else
            data.putExtra(ACCESS_LIST, new ArrayList<>(_adapter.getData()));
        setResult(RESULT_OK, data);
    }

    @Override
    public NotificationsViewModel getViewModel() {  return _notificationsViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_notifications; }
}