package com.example.sauravrp.listings.repo;

import com.example.sauravrp.listings.repo.interfaces.IDataModel;
import com.example.sauravrp.listings.helpers.interfaces.ISchedulerProvider;
import com.example.sauravrp.listings.network.models.Listing;
import com.example.sauravrp.listings.network.YahooAPI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class DataRepo implements IDataModel {

    private final static int FETCH_SIZE = 15;

    YahooAPI yahooAPI;
    ISchedulerProvider schedulerProvider;

    public DataRepo(YahooAPI yahooAPI, ISchedulerProvider aSchedulerProvider) {
        this.yahooAPI = yahooAPI;
        this.schedulerProvider = aSchedulerProvider;
    }

    @Override
    public Single<List<Listing>> getListings(int offset) {
        String query = String.format("select * from local.search(%d,%d) where zip='78759' and query='pizza'", offset, FETCH_SIZE);
        return yahooAPI.getQueryResults(query, "json", true, "")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .flatMap(resultQuery -> {
                    ArrayList<Listing> results;
                            if (resultQuery != null
                                    && resultQuery.getQueryInfo() != null
                                    && resultQuery.getQueryInfo().getResults() != null
                                    && resultQuery.getQueryInfo().getResults().getListings() != null) {
                                results = resultQuery.getQueryInfo().getResults().getListings();
                            } else {
                                results = new ArrayList<>();
                            }
                    return Single.just(results);
                        }
                );
    }
}
