package com.alexeyburyanov.smarthotel.ui.main;

import android.Manifest;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.nfc.NfcAdapter;
import android.widget.Toast;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.ui.base.BaseViewModel;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableField<String> _appVersion = new ObservableField<>();
    private final ObservableField<String> _userName = new ObservableField<>();
    private final ObservableField<String> _userEmail = new ObservableField<>();
    private final ObservableField<String> _userProfilePicUrl = new ObservableField<>();
    private final ObservableBoolean _isBooking = new ObservableBoolean(false);
    private NfcAdapter _nfcAdapter;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        // Определяем из настроек есть ли бронь
        setIsBooking(dataManager.getBooking());
    }

    public ObservableBoolean getIsBooking() { return _isBooking; }
    public void setIsBooking(boolean isBooking) { _isBooking.set(isBooking); }

    public ObservableField<String> getAppVersion() { return _appVersion; }
    public void updateAppVersion(String version) { _appVersion.set(version); }

    public ObservableField<String> getUserName() { return _userName; }
    public void setUserName(String userName) { _userName.set(userName); }

    public ObservableField<String> getUserEmail() { return _userEmail; }
    public void setUserEmail(String email) { _userEmail.set(email); }

    public ObservableField<String> getUserProfilePicUrl() { return _userProfilePicUrl; }
    public void setUserProfilePicUrl(String picUrl) { _userProfilePicUrl.set(picUrl); }

    public void onOpenDoor() {
        MainActivity mainActivity = getNavigator().getMainActivity();
        if (!mainActivity.hasPermission(Manifest.permission.NFC)) {
            mainActivity.requestPermissionsSafely(new String[] { Manifest.permission.NFC }, 100);
        } // if
        _nfcAdapter = NfcAdapter.getDefaultAdapter(mainActivity);
        if (_nfcAdapter == null) {
            Toast.makeText(mainActivity, "Устройство не поддерживает NFC!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!_nfcAdapter.isEnabled()) {
            Toast.makeText(mainActivity, "NFC отключен!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mainActivity, "В разработке...", Toast.LENGTH_LONG).show();
        }
    }

    public void onGoToMyRoom() {
        new android.os.Handler().post(() -> {
            getNavigator().openMyRoomActivity();
        });
    }

    public void onBooking() {
        new android.os.Handler().post(() -> {
            getNavigator().openBookingActivity();
        });
    }

    public void onNavMenuCreated() {
        final String currentUserName = getDataManager().getCurrentUserName();
        if (currentUserName != null && !currentUserName.isEmpty()) {
            setUserName(currentUserName);
        }
        final String currentUserEmail = getDataManager().getCurrentUserEmail();
        if (currentUserEmail != null && !currentUserEmail.isEmpty()) {
            setUserEmail(currentUserEmail);
        }
        final String profilePicUrl = getDataManager().getCurrentUserProfilePicUrl();
        if (profilePicUrl != null && !profilePicUrl.isEmpty()) {
            setUserProfilePicUrl(profilePicUrl);
        }
    }

    public void logout() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().doLogoutApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getDataManager().setUserAsLoggedOut();
                    setIsLoading(false);
                    getNavigator().openLoginActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }
}