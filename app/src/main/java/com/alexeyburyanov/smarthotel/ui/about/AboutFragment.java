package com.alexeyburyanov.smarthotel.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.databinding.FragmentAboutBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * Фрагмент диалога "О приложении"
 */
public class AboutFragment extends BaseFragment<FragmentAboutBinding, AboutViewModel> implements AboutNavigator {

    @Inject AboutViewModel _aboutViewModel;

    public static final String TAG = "AboutFragment";

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _aboutViewModel.setNavigator(this);
    }

    @Override
    public AboutViewModel getViewModel() { return _aboutViewModel; }

    @Override
    public int getBindingVariable() { return BR.viewModel; }

    @Override
    public int getLayoutId() { return R.layout.fragment_about; }

    @Override
    public void goBack() { getBaseActivity().onFragmentDetached(TAG); }

    @Override
    public void onDestroyView() { super.onDestroyView(); }
}