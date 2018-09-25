package com.example.sauravrp.pizzame.network;

import com.example.sauravrp.pizzame.models.ResultQuery;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YahooAPI {

    @GET("yql")
    Observable<ResultQuery> getQueryResults(@Query("q") String queryValue,
                                            @Query("format") String format,
                                            @Query("diagnostics") boolean diagnostics,
                                            @Query("callback") String callback);
}
