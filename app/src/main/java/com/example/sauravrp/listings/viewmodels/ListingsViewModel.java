package com.example.sauravrp.listings.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.sauravrp.listings.network.models.Listing;
import com.example.sauravrp.listings.repo.interfaces.IDataModel;
import com.example.sauravrp.listings.views.models.ListingsUiModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class ListingsViewModel extends ViewModel {

    private IDataModel dataModel;

    private final PublishSubject<Integer> resultsSubject = PublishSubject.create();
    private final MutableLiveData<ListingsUiModel> resultSelected = new MutableLiveData<>();

    public ListingsViewModel(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public Observable<List<ListingsUiModel>> getListings() {
        return resultsSubject
                .flatMap(offset -> dataModel.getListings(offset).toObservable())
                .flatMap(list -> Observable.just(createUiModel(list)));
    }

    public LiveData<ListingsUiModel> getSelectedListing() {
        return resultSelected;
    }


    public void getMoreListings(final int offset) {
        resultsSubject.onNext(offset);
    }

    public void selectListing(ListingsUiModel resultUiModel) {
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
