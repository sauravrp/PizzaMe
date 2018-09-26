package com.example.sauravrp.pizzame.di.modules.views;

import com.example.sauravrp.pizzame.repo.interfaces.IDataModel;
import com.example.sauravrp.pizzame.viewmodels.ListingDetailViewModel;
import com.example.sauravrp.pizzame.viewmodels.ListingsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    ListingsViewModel providesListingsViewModel(IDataModel dataModel) {
        return new ListingsViewModel(dataModel);
    }

    @Provides
    ListingDetailViewModel providesListingDetailViewModel() {
        return new ListingDetailViewModel();
    }
}
