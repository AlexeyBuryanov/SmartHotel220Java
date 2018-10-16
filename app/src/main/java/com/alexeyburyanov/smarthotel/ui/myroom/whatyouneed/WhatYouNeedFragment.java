package com.alexeyburyanov.smarthotel.ui.myroom.whatyouneed;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.databinding.FragmentWhatYouNeedBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
public class WhatYouNeedFragment extends BaseFragment<FragmentWhatYouNeedBinding, WhatYouNeedViewModel>
        implements WhatYouNeedNavigator {

    @Inject ViewModelProvider.Factory _viewModelFactory;
    FragmentWhatYouNeedBinding _fragmentWhatYouNeedBinding;

    private WhatYouNeedViewModel _whatYouNeedViewModel;
    private boolean _tbIceValue = false;
    private boolean _tbToothbrushValue = false;
    private boolean _tbTowelsValue = false;
    private boolean _tbLeakValue = false;

    public static WhatYouNeedFragment newInstance() {
        Bundle args = new Bundle();
        WhatYouNeedFragment fragment = new WhatYouNeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _whatYouNeedViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _fragmentWhatYouNeedBinding = getViewDataBinding();

        setUpButtons();
    }

    private void setUpButtons() {
        _fragmentWhatYouNeedBinding.tbIce.setBackgroundResource(R.mipmap.ic_ice_off);
        _fragmentWhatYouNeedBinding.tbIce.setOnClickListener(v -> {
            if (_tbIceValue) {
                _fragmentWhatYouNeedBinding.tbIce.setBackgroundResource(R.mipmap.ic_ice_off);
                _tbIceValue = false;
            } else {
                _fragmentWhatYouNeedBinding.tbIce.setBackgroundResource(R.mipmap.ic_ice_on);
                _tbIceValue = true;
            }
        });

        _fragmentWhatYouNeedBinding.tbToothbrush.setBackgroundResource(R.mipmap.ic_toothbrush_off);
        _fragmentWhatYouNeedBinding.tbToothbrush.setOnClickListener(v -> {
            if (_tbToothbrushValue) {
                _fragmentWhatYouNeedBinding.tbToothbrush.setBackgroundResource(R.mipmap.ic_toothbrush_off);
                _tbToothbrushValue = false;
            } else {
                _fragmentWhatYouNeedBinding.tbToothbrush.setBackgroundResource(R.mipmap.ic_toothbrush_on);
                _tbToothbrushValue = true;
            }
        });

        _fragmentWhatYouNeedBinding.tbTowels.setBackgroundResource(R.mipmap.ic_towels_off);
        _fragmentWhatYouNeedBinding.tbTowels.setOnClickListener(v -> {
            if (_tbTowelsValue) {
                _fragmentWhatYouNeedBinding.tbTowels.setBackgroundResource(R.mipmap.ic_towels_off);
                _tbTowelsValue = false;
            } else {
                _fragmentWhatYouNeedBinding.tbTowels.setBackgroundResource(R.mipmap.ic_towels_on);
                _tbTowelsValue = true;
            }
        });

        _fragmentWhatYouNeedBinding.tbLeak.setBackgroundResource(R.mipmap.ic_leak_off);
        _fragmentWhatYouNeedBinding.tbLeak.setOnClickListener(v -> {
            if (_tbLeakValue) {
                _fragmentWhatYouNeedBinding.tbLeak.setBackgroundResource(R.mipmap.ic_leak_off);
                _tbLeakValue = false;
            } else {
                _fragmentWhatYouNeedBinding.tbLeak.setBackgroundResource(R.mipmap.ic_leak_on);
                _tbLeakValue = true;
            }
        });
    }

    @Override
    public WhatYouNeedViewModel getViewModel() {
        _whatYouNeedViewModel = ViewModelProviders.of(this, _viewModelFactory).get(WhatYouNeedViewModel.class);
        return _whatYouNeedViewModel;
    }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.fragment_what_you_need; }
}
