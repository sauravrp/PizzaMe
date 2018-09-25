package di.modules.network;

import com.example.sauravrp.pizzame.network.YahooAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class YahooAPIModule {

    YahooAPI yahooAPI;

    @Provides
    YahooAPI providesYahooAPI(Retrofit retrofit) {
        if(yahooAPI == null) {
            yahooAPI = retrofit.create(YahooAPI.class);
        }
        return yahooAPI;
    }
}
