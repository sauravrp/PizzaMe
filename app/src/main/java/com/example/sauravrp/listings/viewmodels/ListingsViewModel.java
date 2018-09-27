package com.example.sauravrp.listings.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;
import android.util.Pair;

import com.example.sauravrp.listings.network.models.Listing;
import com.example.sauravrp.listings.repo.interfaces.IDataModel;
import com.example.sauravrp.listings.views.models.ListingsUiModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class ListingsViewModel extends ViewModel {

    private IDataModel dataModel;

    private Location location;

    private final PublishSubject<Pair<Location, Integer>> resultsSubject = PublishSubject.create();
    private final MutableLiveData<ListingsUiModel> resultSelected = new MutableLiveData<>();

    public ListingsViewModel(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public Observable<List<ListingsUiModel>> getListings() {
        return resultsSubject
                .flatMap(pair -> dataModel.getListings(pair.first.getLatitude(),
                        pair.first.getLongitude(),
                        pair.second).toObservable())
                .flatMap(list -> Observable.just(createUiModel(list)));
    }

    public LiveData<ListingsUiModel> getSelectedListing() {
        return resultSelected;
    }


    public void getMoreListings(final Location location, final int offset) {
        if(location != null) {
            this.location = location;
            resultsSubject.onNext(Pair.create(location, offset));
        }
    }

    public void getMoreListings(final int offset) {
        if(location != null) {
            resultsSubject.onNext(Pair.create(location, offset));
        }
    }

    public void selectListing(final ListingsUiModel resultUiModel) {
        resultSelected.setValue(resultUiModel);
    }

    private List<ListingsUiModel> createUiModel(List<Listing> results) {
        ArrayList<ListingsUiModel> newList = new ArrayList<>();
        for (Listing item : results) {
            newList.add(new ListingsUiModel(item.getId(),
                    item.getTitle(),
                    item.getAddress(),
                    item.getCity(),
                    item.getState(),
                    item.getPhone(),
                    item.getDistance()));
        }

        return newList;
    }
}
