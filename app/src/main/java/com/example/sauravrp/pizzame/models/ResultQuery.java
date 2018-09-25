package com.example.sauravrp.pizzame.models;

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
