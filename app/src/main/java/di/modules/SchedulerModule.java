package di.modules;

import com.example.sauravrp.pizzame.helpers.SchedulerProvider;
import com.example.sauravrp.pizzame.helpers.interfaces.ISchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

   private ISchedulerProvider schedulerProvider;

    @Provides
    ISchedulerProvider provider() {
        if(schedulerProvider == null) {
            schedulerProvider = new SchedulerProvider();
        }
        return schedulerProvider;
    }
}
