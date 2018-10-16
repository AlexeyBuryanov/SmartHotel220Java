package com.alexeyburyanov.smarthotel.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * База для фрагментов.
 */
public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    private BaseActivity _activity;
    private T _viewDataBinding;
    private V _viewModel;
    private View _rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        _viewModel = getViewModel();
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        _rootView = _viewDataBinding.getRoot();
        return _rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _viewDataBinding.setVariable(getBindingVariable(), _viewModel);
        _viewDataBinding.executePendingBindings();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            _activity = activity;
            activity.onFragmentAttached();
        } // if
    }

    @Override
    public void onDetach() {
        _activity = null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() { return _activity; }

    public T getViewDataBinding() { return _viewDataBinding; }

    public boolean isNetworkConnected() {
        return _activity != null && _activity.isNetworkConnected();
    }

    public void hideKeyboard() {
        if (_activity != null) {
            _activity.hideKeyboard();
        } // if
    }

    public void openActivityOnTokenExpire() {
        if (_activity != null) {
            _activity.openActivityOnTokenExpire();
        } // if
    } // if

    private void performDependencyInjection() { AndroidSupportInjection.inject(this); }

    public abstract V getViewModel();

    public abstract int getBindingVariable();

    @LayoutRes public abstract int getLayoutId();

    /** Интерфейс обратного вызова*/
    public interface Callback {

        void onFragmentAttached();
        void onFragmentDetached(String tag);
    }
}