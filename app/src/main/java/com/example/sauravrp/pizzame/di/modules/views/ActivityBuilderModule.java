package com.example.sauravrp.pizzame.di.modules.views;

import com.example.sauravrp.pizzame.views.ListingsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract ListingsActivity bindPizzaMeView();
}
