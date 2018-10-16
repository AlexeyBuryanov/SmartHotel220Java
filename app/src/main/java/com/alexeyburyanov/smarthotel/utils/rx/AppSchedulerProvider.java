package com.alexeyburyanov.smarthotel.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * Провайдер планировщика.
 */
public class AppSchedulerProvider implements SchedulerProvider {

    /**Планировщик, который выполняет действия в основной теме Android.*/
    @Override
    public Scheduler ui() { return AndroidSchedulers.mainThread(); }

    /**
     * Возвращает общий экземпляр планировщика, предназначенный для
     * вычислительной работы. Это можно использовать для циклов событий,
     * обработки обратных вызовов и другой вычислительной работы.
     * */
    @Override
    public Scheduler computation() { return Schedulers.computation(); }

    /**
     * Возвращает общий экземпляр планировщика, предназначенный для работы
     * с привязкой к IO. Это можно использовать для асинхронного выполнения блокировки IO.
     * */
    @Override
    public Scheduler io() { return Schedulers.io(); }
}