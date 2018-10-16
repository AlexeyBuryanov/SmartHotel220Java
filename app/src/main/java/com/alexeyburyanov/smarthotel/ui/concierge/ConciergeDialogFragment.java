package com.alexeyburyanov.smarthotel.ui.concierge;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.ui.base.BaseDialog;

/**
 * Created by Alexey Buryanov 09.04.2018.
 */
public class ConciergeDialogFragment extends BaseDialog implements View.OnClickListener {

    public static final String TAG = "ConciergeDialogFragment";

    public static ConciergeDialogFragment newInstance() {
        Bundle args = new Bundle();
        ConciergeDialogFragment fragment = new ConciergeDialogFragment();
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
        View view = inflater.inflate(R.layout.fragment_dialog_concierge, container, false);
        view.findViewById(R.id.btnSkype).setOnClickListener(this);
        view.findViewById(R.id.btnTeleg).setOnClickListener(this);
        view.findViewById(R.id.btnCancel).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        // TODO:
        switch (v.getId()) {
            case R.id.btnSkype:
                dismiss();
                break;
            case R.id.btnTeleg:
                dismiss();
                break;
            case R.id.btnCancel:
                getDialog().cancel();
                break;
        } // switch
    } // onClick
}
