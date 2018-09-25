package di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;
    public ApplicationModule(Application aApp) {
        application = aApp;
    }

    @Provides
    @Named("app_context")
    Context appContext() {
        return application.getApplicationContext();
    }

    @Provides
    Application application() {
        return application;
    }
}
