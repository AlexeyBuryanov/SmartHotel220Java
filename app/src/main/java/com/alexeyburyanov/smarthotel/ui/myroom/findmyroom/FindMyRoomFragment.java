package com.alexeyburyanov.smarthotel.ui.myroom.findmyroom;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.databinding.FragmentFindMyRoomBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
public class FindMyRoomFragment extends BaseFragment<FragmentFindMyRoomBinding, FindMyRoomViewModel>
        implements FindMyRoomNavigator {

    @Inject ViewModelProvider.Factory _viewModelFactory;
    FragmentFindMyRoomBinding _fragmentFindMyRoomBinding;

    private FindMyRoomViewModel _findMyRoomViewModel;

    public static FindMyRoomFragment newInstance() {
        Bundle args = new Bundle();
        FindMyRoomFragment fragment = new FindMyRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _findMyRoomViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _fragmentFindMyRoomBinding = getViewDataBinding();
    }

    @Override
    public FindMyRoomViewModel getViewModel() {
        _findMyRoomViewModel = ViewModelProviders.of(this, _viewModelFactory).get(FindMyRoomViewModel.class);
        return _findMyRoomViewModel;
    }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.fragment_find_my_room; }
}
