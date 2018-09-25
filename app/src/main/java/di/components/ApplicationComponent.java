package di.components;

import android.app.Application;
import android.content.Context;

import com.example.sauravrp.pizzame.PizzaMeApplication;

import javax.inject.Named;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import di.modules.ApplicationModule;
import di.modules.network.RetrofitModule;
import di.modules.network.YahooAPIModule;
import di.modules.services.DataServiceModule;
import di.modules.views.ActivityBuilderModule;
import di.modules.views.ViewModelModule;

@Component(modules = {ApplicationModule.class, AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        // these can be in NetworkComponent if need be
        RetrofitModule.class, YahooAPIModule.class,
        // data service layer
        DataServiceModule.class,
        // view model
        ViewModelModule.class})
public interface ApplicationComponent {

    Application application();
    @Named("app_context")
    Context appContext();

    void inject(PizzaMeApplication app);
}
