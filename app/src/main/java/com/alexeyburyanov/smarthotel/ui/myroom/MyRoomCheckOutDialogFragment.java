package com.alexeyburyanov.smarthotel.ui.myroom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.ui.base.BaseDialog;

/**
 * Created by Alexey Buryanov 04.04.2018.
 */
public class MyRoomCheckOutDialogFragment extends BaseDialog implements View.OnClickListener {

    private MyRoomCheckOutDialogData _data;
    private static final String USER_NAME = "USER_NAME";
    public static final String TAG = "MyRoomCheckOutDialogFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _data = (MyRoomCheckOutDialogData)context;
    }

    public static MyRoomCheckOutDialogFragment newInstance(String userName) {
        Bundle args = new Bundle();
        args.putString(USER_NAME, userName);
        MyRoomCheckOutDialogFragment fragment = new MyRoomCheckOutDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_check_out, container, false);
        view.findViewById(R.id.btnMakeNewBook).setOnClickListener(this);
        view.findViewById(R.id.btnCheckOut).setOnClickListener(this);
        TextView tvText = view.findViewById(R.id.tvText);
        tvText.setText(String.format("\nСпасибо,\n %s", getArguments().getString(USER_NAME)));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMakeNewBook:
                _data.onBookingClick();
                dismiss();
                break;
            case R.id.btnCheckOut:
                _data.onCheckOutClick();
                dismiss();
                break;
        } // switch
    } // onClick
}
