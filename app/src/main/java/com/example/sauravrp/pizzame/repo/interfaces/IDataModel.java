package com.example.sauravrp.pizzame.repo.interfaces;

import com.example.sauravrp.pizzame.network.models.Listing;

import java.util.List;

import io.reactivex.Single;

public interface IDataModel {

    Single<List<Listing>> getListings(int offset);
}
