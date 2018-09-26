package com.example.sauravrp.pizzame.datamodels.interfaces;

import com.example.sauravrp.pizzame.models.network.Result;

import java.util.List;

import io.reactivex.Single;

public interface IDataModel {

    Single<List<Result>> getPizzaListings(int offset);
}
