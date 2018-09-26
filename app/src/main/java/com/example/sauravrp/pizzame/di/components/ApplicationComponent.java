package com.example.sauravrp.pizzame.di.components;

import com.example.sauravrp.pizzame.PizzaMeApplication;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import com.example.sauravrp.pizzame.di.modules.SchedulerModule;
import com.example.sauravrp.pizzame.di.modules.network.RetrofitModule;
import com.example.sauravrp.pizzame.di.modules.network.YahooAPIModule;
import com.example.sauravrp.pizzame.di.modules.services.DataServiceModule;
import com.example.sauravrp.pizzame.di.modules.views.ActivityBuilderModule;
import com.example.sauravrp.pizzame.di.modules.views.ViewModelModule;

@Component(modules = {AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        // these can be in NetworkComponent if need be
        RetrofitModule.class, YahooAPIModule.class,
        // data service layer
        DataServiceModule.class,
        // view model
        ViewModelModule.class,
        // helper
        SchedulerModule.class})
public abstract class ApplicationComponent {

    public abstract void inject(PizzaMeApplication app);
}
