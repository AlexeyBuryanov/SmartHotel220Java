package com.alexeyburyanov.smarthotel.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.alexeyburyanov.smarthotel.data.DataManager;
import com.alexeyburyanov.smarthotel.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * База для моделей представления.
 */
public abstract class BaseViewModel<N> extends ViewModel {

    /** навигатор*/
    private N _navigator;
    /** менеджер данных*/
    private final DataManager _dataManager;
    /** планировщик*/
    private final SchedulerProvider _schedulerProvider;
    /** идёт ли сейчас загрузка*/
    private final ObservableBoolean _isLoading = new ObservableBoolean(false);
    /** контейнер для хранения множества disposable материалов. предлагает добавление и удаление*/
    private CompositeDisposable _compositeDisposable;

    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        _dataManager = dataManager;
        _schedulerProvider = schedulerProvider;
        _compositeDisposable = new CompositeDisposable();
    }

    public void setNavigator(N navigator) { _navigator = navigator; }
    public N getNavigator() { return _navigator; }

    public DataManager getDataManager() { return _dataManager; }

    public SchedulerProvider getSchedulerProvider() { return _schedulerProvider; }

    public CompositeDisposable getCompositeDisposable() { return _compositeDisposable; }

    public ObservableBoolean getIsLoading() { return _isLoading; }

    public void setIsLoading(boolean isLoading) { _isLoading.set(isLoading); }

    @Override
    protected void onCleared() {
        _compositeDisposable.dispose();
        super.onCleared();
    }
}