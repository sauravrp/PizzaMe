package di.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import di.modules.views.ActivityBuilderModule;
import di.modules.ApplicationModule;

@Component(modules = {ApplicationModule.class, AndroidInjectionModule.class, ActivityBuilderModule.class})
public interface ApplicationComponent {

    Application application();
    @Named("app_context")
    Context appContext();
}
