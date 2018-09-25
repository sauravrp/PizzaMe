package di.modules;


import android.app.Activity;

import com.example.sauravrp.pizzame.views.PizzaMeActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import di.components.PizzaMeActivitySubcomponent;

@Module(subcomponents = PizzaMeActivitySubcomponent.class)
public abstract class BindingModule {

    @Binds
    @IntoMap
    @ActivityKey(PizzaMeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindPizzaMeActivityInjectorFactory(PizzaMeActivitySubcomponent.Builder builder);
}
