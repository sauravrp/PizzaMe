package com.example.sauravrp.pizzame.helpers.interfaces;

import io.reactivex.Scheduler;

public interface ISchedulerProvider {
    Scheduler computation();
    Scheduler io();
    Scheduler ui();
}
