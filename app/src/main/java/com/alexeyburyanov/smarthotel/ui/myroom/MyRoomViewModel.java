package com.alexeyburyanov.smarthotel.ui.myroom;

import android.Manifest;
import android.nfc.NfcAdapter;
import android.widget.Toast;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey Buryanov 02.04.2018.
 */
public class MyRoomViewModel extends BaseViewModel<MyRoomNavigator> {

    private NfcAdapter _nfcAdapter;

    public MyRoomViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }

    public void onOpenDoor() {
        MyRoomActivity myRoomActivity = getNavigator().getMyRoomActivity();
        if (!myRoomActivity.hasPermission(Manifest.permission.NFC)) {
            myRoomActivity.requestPermissionsSafely(new String[] { Manifest.permission.NFC }, 100);
        } // if
        _nfcAdapter = NfcAdapter.getDefaultAdapter(myRoomActivity);
        if (_nfcAdapter == null) {
            Toast.makeText(myRoomActivity, "Устройство не поддерживает NFC!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!_nfcAdapter.isEnabled()) {
            Toast.makeText(myRoomActivity, "NFC отключен!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(myRoomActivity, "В разработке...", Toast.LENGTH_LONG).show();
        }
    }
}
