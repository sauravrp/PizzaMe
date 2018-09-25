package com.example.sauravrp.pizzame.datamodels;

import com.example.sauravrp.pizzame.models.Result;
import com.example.sauravrp.pizzame.network.YahooAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class YahooDataService implements IDataModel {

    private final static int FETCH_SIZE = 15;

    YahooAPI yahooAPI;

    public YahooDataService(YahooAPI yahooAPI) {
        this.yahooAPI = yahooAPI;
    }

    @Override
    public Observable<List<Result>> getPizzaListings(int offset) {
        String query = String.format("select * from local.search(%d,%d) where zip='78759' and query='pizza'", offset, FETCH_SIZE);
        return yahooAPI.getQueryResults(query, "json", true, "")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                    return Observable.just(results);
                        }
                );
    }
}
