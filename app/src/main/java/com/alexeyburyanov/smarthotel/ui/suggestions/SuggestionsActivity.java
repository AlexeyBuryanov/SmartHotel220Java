package com.alexeyburyanov.smarthotel.ui.suggestions;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.items.SuggestionItem;
import com.alexeyburyanov.smarthotel.databinding.ActivitySuggestionsBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by Alexey Buryanov 08.04.2018.
 */
public class SuggestionsActivity extends BaseActivity<ActivitySuggestionsBinding, SuggestionsViewModel>
        implements SuggestionsNavigator, OnMapReadyCallback, SuggestionsAdapter.ItemClickListener {

    @Inject SuggestionsViewModel _suggestionsViewModel;
    @Inject SuggestionsAdapter _adapter;
    @Inject LinearLayoutManager _layoutManager;
    ActivitySuggestionsBinding _activitySuggestionsBinding;

    private SuggestionItem _selectedItem;
    private GoogleMap _googleMap;
    private MarkerOptions _marker1;
    private MarkerOptions _marker2;
    private MarkerOptions _marker3;
    private String[] _permissions = new String[] {
            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @NonNull
    public static Intent getStartIntent(Context context) {
        return new Intent(context, SuggestionsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activitySuggestionsBinding = getViewDataBinding();
        _suggestionsViewModel.setNavigator(this);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        for (int i = 0; i < _permissions.length; i++) {
            if (!hasPermission(_permissions[i])) {
                requestPermissionsSafely(new String[] { _permissions[i] }, i+200);
            }
        }

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        _adapter.getData().add(new SuggestionItem(R.mipmap.img_1, "Солёный цеплёнок",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit", 4, 81));
        _adapter.getData().add(new SuggestionItem(R.mipmap.img_2, "Спортзал",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit", 4, 64));
        _adapter.getData().add(new SuggestionItem(R.mipmap.img_3, "Развлекательный центр",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit", 3, 75));
        _adapter.setClickListener(this);
        _layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        _activitySuggestionsBinding.recyclerViewSuggestions.setLayoutManager(_layoutManager);
        _activitySuggestionsBinding.recyclerViewSuggestions.setItemAnimator(new DefaultItemAnimator());
        _activitySuggestionsBinding.recyclerViewSuggestions.setAdapter(_adapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _googleMap = googleMap;
        _googleMap.setMinZoomPreference(12);
        _googleMap.setIndoorEnabled(true);

        UiSettings uiSettings = _googleMap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        LatLng place1 = new LatLng(47.604941, -122.329695);
        LatLng place2 = new LatLng(47.616065, -122.300413);
        LatLng place3 = new LatLng(47.623007, -122.335802);
        LatLng camFocus = new LatLng(47.604129, -122.314006);

        _marker1 = new MarkerOptions().position(place1).title("Спортзал");
        _marker2 = new MarkerOptions().position(place2).title("Солёный цеплёнок");
        _marker3 = new MarkerOptions().position(place3).title("Развлекательный центр");

        _googleMap.addMarker(_marker1);
        _googleMap.addMarker(_marker2);
        _googleMap.addMarker(_marker3);
        _googleMap.moveCamera(CameraUpdateFactory.newLatLng(camFocus));
    }

    @Override
    public void onItemClick(View view, int position) {
        _selectedItem = _adapter.getItem(position);

        if (Objects.equals(_selectedItem.get_name(), _marker1.getTitle())) {
            _googleMap.moveCamera(CameraUpdateFactory.newLatLng(_marker1.getPosition()));
        } else if (Objects.equals(_selectedItem.get_name(), _marker2.getTitle())) {
            _googleMap.moveCamera(CameraUpdateFactory.newLatLng(_marker2.getPosition()));
        } else if (Objects.equals(_selectedItem.get_name(), _marker3.getTitle())) {
            _googleMap.moveCamera(CameraUpdateFactory.newLatLng(_marker3.getPosition()));
        }
        _googleMap.setMinZoomPreference(13);
    }

    @Override
    public SuggestionsViewModel getViewModel() { return _suggestionsViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.activity_suggestions; }
}
