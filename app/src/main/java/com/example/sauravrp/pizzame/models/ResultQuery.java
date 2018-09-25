package com.example.sauravrp.pizzame.models;

import java.util.ArrayList;

public class ResultQuery {

    private int count;
    private ArrayList<Result> results;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
