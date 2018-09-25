package di.modules.views;

import com.example.sauravrp.pizzame.views.PizzaMeView;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract PizzaMeView bindPizzaMeView();
}
