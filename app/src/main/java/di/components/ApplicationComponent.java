package di.components;

import com.example.sauravrp.pizzame.PizzaMeApplication;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import di.modules.network.RetrofitModule;
import di.modules.network.YahooAPIModule;
import di.modules.services.DataServiceModule;
import di.modules.views.ActivityBuilderModule;
import di.modules.views.ViewModelModule;

@Component(modules = {AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        // these can be in NetworkComponent if need be
        RetrofitModule.class, YahooAPIModule.class,
        // data service layer
        DataServiceModule.class,
        // view model
        ViewModelModule.class})
public interface ApplicationComponent {

    void inject(PizzaMeApplication app);
}
