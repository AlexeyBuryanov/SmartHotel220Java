package com.alexeyburyanov.smarthotel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Created by Alexey Buryanov 19.02.2018.
 *
 * Провайдер фабрики, который сохраняет ViewModels {@link ViewModel}.
 * Используется, если viewmodel имеет параметризованный конструктор.
 */
public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {

    private V _viewModel;

    public ViewModelProviderFactory(V viewModel) { _viewModel = viewModel; }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // Проверяем могут ли объекты viewModel назначаться объектам model
        if (modelClass.isAssignableFrom(_viewModel.getClass())) {
            // Возвращаем viewModel
            return (T)_viewModel;
        } // if
        throw new IllegalArgumentException("Неизвестное имя класса");
    }

}