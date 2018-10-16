package com.alexeyburyanov.smarthotel.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.db.User;
import com.alexeyburyanov.smarthotel.databinding.ActivityLoginBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;
import com.alexeyburyanov.smarthotel.ui.main.MainActivity;
import com.github.mrengineer13.snackbar.SnackBar;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject LoginViewModel _loginViewModel;
    ActivityLoginBinding _activityLoginBinding;

    private SnackBar.Builder _snackbar;

    @NonNull
    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activityLoginBinding = getViewDataBinding();
        _loginViewModel.setNavigator(this);
        _snackbar = new SnackBar.Builder(this);
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void login() {
        //getViewModel().getDataManager().insertUser(new User(500L,"alexeyburyanov@gmail.com",
        //        Calendar.getInstance().getTime().toString(), Calendar.getInstance().getTime().toString()));

        String email = _activityLoginBinding.etEmail.getText().toString();
        String password = _activityLoginBinding.etPassword.getText().toString();

        if (!_loginViewModel.isEmailEmpty(email)) {
            _activityLoginBinding.etEmail.setError("Введите e-mail");
            _snackbar.withMessage("Введите e-mail").show();
            return;
        } // if
        if (!_loginViewModel.isEmailValid(email)) {
            _activityLoginBinding.etEmail.setError("Введите корректный e-mail");
            _snackbar.withMessage("Введите корректный e-mail").show();
            return;
        } // if
        if (!_loginViewModel.isPasswordEmpty(password)) {
            _activityLoginBinding.etPassword.setError("Пароль не должен быть пустым");
            _snackbar.withMessage("Пароль не должен быть пустым").show();
            return;
        } // if

        hideKeyboard();
        showLoading();
        getViewModel().getCompositeDisposable().add(getViewModel()
                .getDataManager()
                .findUser(email)
                .subscribeOn(getViewModel().getSchedulerProvider().io())
                .observeOn(getViewModel().getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    if (!aBoolean) {
                        _activityLoginBinding.etEmail.setError("Нет такого пользователя");
                        _snackbar.withMessage("Нет такого пользователя в базе").show();
                    } else {
                        _loginViewModel.login(email, password);
                    } // if
                    hideLoading();
                }, throwable -> {
                    _snackbar.withMessage("Ошибка при поиске пользователя в базе").show();
                    hideLoading();
                }));
    }

    @Override
    public void handleError(Throwable throwable) {}

    @Override
    public LoginViewModel getViewModel() { return _loginViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_login; }
}