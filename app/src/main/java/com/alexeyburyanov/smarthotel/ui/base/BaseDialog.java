package com.alexeyburyanov.smarthotel.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * База для диалогов.
 */
public abstract class BaseDialog extends DialogFragment {

    private BaseActivity _activity;

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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Контент
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Создание полноэкранного диалога
        Context context = getContext();
        assert context != null;
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        } // if
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /** Показать загрузку*/
    public void showLoading() {
        if (_activity != null) {
            _activity.showLoading();
        } // if
    }

    /** Скрыть загрузку*/
    public void hideLoading() {
        if (_activity != null) {
            _activity.hideLoading();
        } // if
    }

    /** Есть ли коннект с сетью (интернетом)*/
    public boolean isNetworkConnected() {
        return _activity != null && _activity.isNetworkConnected();
    }

    /** Скрыть клавиатуру*/
    public void hideKeyboard() {
        if (_activity != null) {
            _activity.hideKeyboard();
        } // if
    }

    /** Открыть активность входа по истечению срока действия токена*/
    public void openActivityOnTokenExpire() {
        if (_activity != null) {
            _activity.openActivityOnTokenExpire();
        } // if
    }

    /** Отображает диалог добавляя фрагмент к данному FragmentManager*/
    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.addToBackStack(null);
        show(transaction, tag);
    }

    /** Отменить диалог*/
    public void dismissDialog(String tag) {
        dismiss();
        getBaseActivity().onFragmentDetached(tag);
    }
}