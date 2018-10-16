package com.alexeyburyanov.smarthotel.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Alexey Buryanov 19.02.2018.
 * Интерфейс поставщика планировщика.
 */
public interface SchedulerProvider {

    Scheduler ui();
    Scheduler computation();
    Scheduler io();
}