package com.example.sauravrp.pizzame.models.network;

import com.example.sauravrp.pizzame.models.network.QueryInfo;
import com.google.gson.annotations.SerializedName;

public class ResultQuery {

    @SerializedName("query")
    private QueryInfo queryInfo;

    public QueryInfo getQueryInfo() {
        return queryInfo;
    }

    public void setQueryInfo(QueryInfo queryInfo) {
        this.queryInfo = queryInfo;
    }
}
