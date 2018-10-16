package com.alexeyburyanov.smarthotel.ui.myroom.ambientset;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.databinding.FragmentAmbientSetBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Alexey Buryanov 05.04.2018.
 */
public class AmbientSetFragment extends BaseFragment<FragmentAmbientSetBinding, AmbientSetViewModel>
        implements AmbientSetNavigator {

    @Inject ViewModelProvider.Factory _viewModelFactory;
    FragmentAmbientSetBinding _fragmentRoomsBinding;

    private AmbientSetViewModel _ambientSetViewModel;

    public static AmbientSetFragment newInstance() {
        Bundle args = new Bundle();
        AmbientSetFragment fragment = new AmbientSetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _ambientSetViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _fragmentRoomsBinding = getViewDataBinding();

        _fragmentRoomsBinding.sbAmbientLight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _fragmentRoomsBinding.tvValueAmbientLight.setText(String.format("%sK", String.valueOf(progress)));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        _fragmentRoomsBinding.sbMusicVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _fragmentRoomsBinding.tvValueMusic.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        _fragmentRoomsBinding.sbTemperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _fragmentRoomsBinding.tvValueTemperature.setText(String.format("%s°C", String.valueOf(progress)));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        _fragmentRoomsBinding.sbWindowBlinds.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _fragmentRoomsBinding.tvValueWindowBlinds.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        _fragmentRoomsBinding.btnEcoMode.setOnClickListener(v -> {
            _fragmentRoomsBinding.sbAmbientLight.setProgress(400);
            _fragmentRoomsBinding.sbMusicVolume.setProgress(20);
            _fragmentRoomsBinding.sbTemperature.setProgress(18);
            _fragmentRoomsBinding.sbWindowBlinds.setProgress(20);
        });
    }

    @Override
    public AmbientSetViewModel getViewModel() {
        _ambientSetViewModel = ViewModelProviders.of(this, _viewModelFactory).get(AmbientSetViewModel.class);
        return _ambientSetViewModel; }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.fragment_ambient_set; }
}
