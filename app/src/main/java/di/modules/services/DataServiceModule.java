package di.modules.services;

import com.example.sauravrp.pizzame.datamodels.IDataModel;
import com.example.sauravrp.pizzame.datamodels.YahooDataService;
import com.example.sauravrp.pizzame.network.YahooAPI;

import dagger.Module;
import dagger.Provides;

@Module
public class DataServiceModule {

    YahooDataService yahooDataService;

    @Provides
    IDataModel providesDataModel(YahooAPI yahooAPI) {
        if(yahooDataService == null) {
            yahooDataService = new YahooDataService(yahooAPI);
        }
        return yahooDataService;
    }
}
