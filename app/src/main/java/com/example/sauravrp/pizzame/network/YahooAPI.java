package com.example.sauravrp.pizzame.network;

import com.example.sauravrp.pizzame.network.models.ResultQuery;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YahooAPI {

    @GET("yql")
    Single<ResultQuery> getQueryResults(@Query("q") String queryValue,
                                        @Query("format") String format,
                                        @Query("diagnostics") boolean diagnostics,
                                        @Query("callback") String callback);
}
