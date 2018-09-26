package com.example.sauravrp.pizzame.datamodels;

import com.example.sauravrp.pizzame.datamodels.interfaces.IDataModel;
import com.example.sauravrp.pizzame.helpers.interfaces.ISchedulerProvider;
import com.example.sauravrp.pizzame.models.network.Result;
import com.example.sauravrp.pizzame.network.YahooAPI;

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
    public Single<List<Result>> getPizzaListings(int offset) {
        String query = String.format("select * from local.search(%d,%d) where zip='78759' and query='pizza'", offset, FETCH_SIZE);
        return yahooAPI.getQueryResults(query, "json", true, "")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .flatMap(resultQuery -> {
                    ArrayList<Result> results;
                            if (resultQuery != null
                                    && resultQuery.getQueryInfo() != null
                                    && resultQuery.getQueryInfo().getResults() != null
                                    && resultQuery.getQueryInfo().getResults().getResults() != null) {
                                results = resultQuery.getQueryInfo().getResults().getResults();
                            } else {
                                results = new ArrayList<>();
                            }
                    return Single.just(results);
                        }
                );
    }
}
