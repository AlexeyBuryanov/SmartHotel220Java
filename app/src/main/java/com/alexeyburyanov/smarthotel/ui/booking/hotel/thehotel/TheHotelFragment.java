package com.alexeyburyanov.smarthotel.ui.booking.hotel.thehotel;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.databinding.FragmentThehotelBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Alexey on 23.03.2018.
 */
public class TheHotelFragment extends BaseFragment<FragmentThehotelBinding, TheHotelViewModel> implements TheHotelNavigator {

    @Inject ViewModelProvider.Factory _viewModelFactory;
    FragmentThehotelBinding _fragmentThehotelBinding;

    private TheHotelViewModel _thehotelViewModel;

    public static TheHotelFragment newInstance() {
        Bundle args = new Bundle();
        TheHotelFragment fragment = new TheHotelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _thehotelViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _fragmentThehotelBinding = getViewDataBinding();
    }

    @Override
    public TheHotelViewModel getViewModel() {
        _thehotelViewModel = ViewModelProviders.of(this, _viewModelFactory).get(TheHotelViewModel.class);
        return _thehotelViewModel;
    }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.fragment_thehotel; }
}