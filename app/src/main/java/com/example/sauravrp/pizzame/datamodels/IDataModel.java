package com.example.sauravrp.pizzame.datamodels;

import com.example.sauravrp.pizzame.models.Result;

import java.util.List;

import io.reactivex.Observable;

public interface IDataModel {

    Observable<List<Result>> getPizzaListings(int offset);
}
