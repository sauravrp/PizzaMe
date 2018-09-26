package com.example.sauravrp.pizzame.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.example.sauravrp.pizzame.datamodels.interfaces.IDataModel;
import com.example.sauravrp.pizzame.models.network.Result;
import com.example.sauravrp.pizzame.models.ui.ResultUiModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


public class PizzaMeViewModel extends ViewModel {

    private IDataModel dataModel;

    private final BehaviorSubject<Integer> resultsSubject = BehaviorSubject.create();

    public PizzaMeViewModel(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public Observable<List<ResultUiModel>> getResults() {
        return resultsSubject
                .flatMap(offset -> dataModel.getPizzaListings(offset).toObservable())
                .flatMap(list -> {
                            ArrayList<ResultUiModel> newList = new ArrayList<>();
                            for (Result item : list) {
                                newList.add(new ResultUiModel(item.getId(),
                                        item.getTitle(),
                                        item.getAddress(),
                                        item.getCity(),
                                        item.getState(),
                                        item.getPhone(),
                                        item.getDistance()));
                            }
                            return Observable.just(newList);
                        }
                );
    }
    

    public void offsetSelected(final int offset) {
        resultsSubject.onNext(offset);
    }

}
