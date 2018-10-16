package com.alexeyburyanov.smarthotel.ui.booking.hotel;

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
 * Created by Alexey on 26.03.2018.
 */
public class BookingHotelDialogFragment extends BaseDialog implements View.OnClickListener {

    private BookingHotelDialogData _data;
    private static final String HOTEL_NAME = "HOTEL_NAME";
    public static final String TAG = "BookingHotelDialogFragment";

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        _data = (BookingHotelDialogData)context;
    }

    public static BookingHotelDialogFragment newInstance(String hotelName) {
        Bundle args = new Bundle();
        args.putString(HOTEL_NAME, hotelName);
        BookingHotelDialogFragment fragment = new BookingHotelDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_booking_hotel, container, false);
        view.findViewById(R.id.btnPositive).setOnClickListener(this);
        view.findViewById(R.id.btnNegative).setOnClickListener(this);
        TextView tvMsg = view.findViewById(R.id.tvMsg);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvMsg.setText(String.format("Вы хотите забронировать номер в отеле \"%s\"", getArguments().getString(HOTEL_NAME)));
        tvTitle.setText("БРОНИРОВАНИЕ");
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPositive:
                _data.onPositiveClick();
                dismiss();
                break;
            case R.id.btnNegative:
                getDialog().cancel();
                break;
        } // switch
    } // onClick
}