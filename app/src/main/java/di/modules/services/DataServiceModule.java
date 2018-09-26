package di.modules.services;

import com.example.sauravrp.pizzame.datamodels.DataRepo;
import com.example.sauravrp.pizzame.datamodels.interfaces.IDataModel;
import com.example.sauravrp.pizzame.helpers.interfaces.ISchedulerProvider;
import com.example.sauravrp.pizzame.network.YahooAPI;

import dagger.Module;
import dagger.Provides;

@Module
public class DataServiceModule {

    DataRepo yahooDataService;

    @Provides
    IDataModel providesDataModel(YahooAPI yahooAPI, ISchedulerProvider schedulerProvider) {
        if(yahooDataService == null) {
            yahooDataService = new DataRepo(yahooAPI, schedulerProvider);
        }
        return yahooDataService;
    }
}
