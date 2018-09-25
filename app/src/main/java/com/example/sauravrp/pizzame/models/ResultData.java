package com.example.sauravrp.pizzame.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultData {

    @SerializedName("Result")
    private ArrayList<Result> results;

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}