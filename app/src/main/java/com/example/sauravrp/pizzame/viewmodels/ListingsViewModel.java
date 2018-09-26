package com.example.sauravrp.pizzame.viewmodels;

import com.example.sauravrp.pizzame.network.models.Listing;
import com.example.sauravrp.pizzame.repo.interfaces.IDataModel;
import com.example.sauravrp.pizzame.views.models.ListingsUiModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class ListingsViewModel {

    private IDataModel dataModel;

    private final PublishSubject<Integer> resultsSubject = PublishSubject.create();
    private final PublishSubject<ListingsUiModel> resultSelected = PublishSubject.create();

    public ListingsViewModel(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public Observable<List<ListingsUiModel>> getListings() {
        return resultsSubject
                .flatMap(offset -> dataModel.getListings(offset).toObservable())
                .flatMap(list -> Observable.just(createUiModel(list)));
    }

    public Observable<ListingsUiModel> getSelectedListing() {
        return resultSelected;
    }


    public void getMoreListings(final int offset) {
        resultsSubject.onNext(offset);
    }

    public void selectListing(ListingsUiModel resultUiModel) {
        resultSelected.onNext(resultUiModel);
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
