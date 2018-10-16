package com.alexeyburyanov.smarthotel.ui.booking.hotel.rooms;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.databinding.FragmentRoomsBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Alexey on 23.03.2018.
 */
public class RoomsFragment extends BaseFragment<FragmentRoomsBinding, RoomsViewModel> implements RoomsNavigator {

    @Inject ViewModelProvider.Factory _viewModelFactory;
    FragmentRoomsBinding _fragmentRoomsBinding;

    private RoomsViewModel _roomsViewModel;

    public static RoomsFragment newInstance() {
        Bundle args = new Bundle();
        RoomsFragment fragment = new RoomsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _roomsViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _fragmentRoomsBinding = getViewDataBinding();
    }

    @Override
    public RoomsViewModel getViewModel() {
        _roomsViewModel = ViewModelProviders.of(this, _viewModelFactory).get(RoomsViewModel.class);
        return _roomsViewModel;
    }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.fragment_rooms; }
}