package di.components;

import com.example.sauravrp.pizzame.views.PizzaMeActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface PizzaMeActivitySubcomponent extends AndroidInjector<PizzaMeActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<PizzaMeActivity> {}
}
