package di.modules.views;

import com.example.sauravrp.pizzame.datamodels.interfaces.IDataModel;
import com.example.sauravrp.pizzame.viewmodels.PizzaMeViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    PizzaMeViewModel providesPizzaMeViewModel(IDataModel dataModel) {
        return new PizzaMeViewModel(dataModel);
    }
}
