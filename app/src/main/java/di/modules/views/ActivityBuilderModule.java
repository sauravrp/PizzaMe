package di.modules.views;

import com.example.sauravrp.pizzame.views.PizzaMeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract PizzaMeActivity bindPizzaMeView();
}
