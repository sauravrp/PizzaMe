package com.example.sauravrp.pizzame.viewmodels;

import com.example.sauravrp.pizzame.datamodels.IDataModel;
import com.example.sauravrp.pizzame.models.Result;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class PizzaMeViewModel {

    private IDataModel dataModel;

    private final BehaviorSubject<Integer> resultsSubject = BehaviorSubject.create();

    public PizzaMeViewModel(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public Observable<List<Result>> getResults() {
        return resultsSubject
                .flatMap(offset -> dataModel.getPizzaListings(offset));
    }

    public void offsetSelected(final int offset) {
        resultsSubject.onNext(offset);
    }

}
