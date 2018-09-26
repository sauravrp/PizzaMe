package com.example.sauravrp.pizzame;

import com.example.sauravrp.pizzame.helpers.interfaces.ISchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ImmediateSchedulerProvider implements ISchedulerProvider {
    @Override
    public Scheduler computation() {
        return Schedulers.single();
    }

    @Override
    public Scheduler io() {
        return Schedulers.single();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.single();
    }
}
