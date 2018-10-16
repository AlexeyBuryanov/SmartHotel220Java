package com.alexeyburyanov.smarthotel.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.alexeyburyanov.smarthotel.ui.login.LoginActivity;
import com.alexeyburyanov.smarthotel.utils.CommonUtils;
import com.alexeyburyanov.smarthotel.utils.NetworkUtils;

import dagger.android.AndroidInjection;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * База для всех активностей.
 */
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends AppCompatActivity implements BaseFragment.Callback {

    // TODO
    // это может вызвать проблемы с переменной isLoading в BaseViewModel,
    // поскольку она будет распространена для всех активностей
    private ProgressDialog _progressDialog;

    private T _viewDataBinding;
    private V _viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    private void performDataBinding() {
        _viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        _viewModel = _viewModel == null ? getViewModel() : _viewModel;
        _viewDataBinding.setVariable(getBindingVariable(), _viewModel);
        _viewDataBinding.executePendingBindings();
    }

    /** Задаёт базовый контекст для этого ContextWrapper.*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /** Безопасный запрос на права*/
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    /** Есть ли право*/
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onFragmentAttached() {}

    @Override
    public void onFragmentDetached(String tag) {}

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } // if
        } // if
    }

    /** Открыть активность входа по истечению срока действия токена*/
    public void openActivityOnTokenExpire() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    /** Есть ли коннект с сетью (интернетом)*/
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    /** Показать загрузку*/
    public void showLoading() {
        hideLoading();
        _progressDialog = CommonUtils.showLoadingDialog(this);
    }

    /** Убрать загрузку*/
    public void hideLoading() {
        if (_progressDialog != null && _progressDialog.isShowing()) {
            _progressDialog.cancel();
        }
    }

    public T getViewDataBinding() { return _viewDataBinding; }

    public abstract V getViewModel();

    public abstract int getBindingVariable();

    @LayoutRes public abstract int getLayoutId();

    public void performDependencyInjection() { AndroidInjection.inject(this); }
}