package com.example.sauravrp.pizzame.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.sauravrp.pizzame.R;
import com.example.sauravrp.pizzame.models.Result;
import com.example.sauravrp.pizzame.network.YahooAPI;
import com.example.sauravrp.pizzame.views.adapters.PizzaPlacesAdapter;
import com.example.sauravrp.pizzame.views.viewhelpers.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PizzaMeActivity extends AppCompatActivity {

    private final String TAG = "PizzaMeActivity";

//    @Inject
//    PizzaMeViewModel pizzaMeViewModel;

    @Inject
    YahooAPI yahooAPI;

    @BindView(R.id.list_view)
    RecyclerView placesListView;

    private PizzaPlacesAdapter pizzaPlacesAdapter;
    private ArrayList<Result> placesList = new ArrayList<>();
    private EndlessRecyclerViewScrollListener mEndlessScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.pizza_me_activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        placesList.clear();
        pizzaPlacesAdapter.notifyDataSetChanged();
        getQueryResults(0);
    }

    private void setupRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        pizzaPlacesAdapter = new PizzaPlacesAdapter(PizzaMeActivity.this, placesList);

        mEndlessScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getQueryResults(totalItemsCount);
            }
        };
        placesListView.setLayoutManager(layoutManager);
        placesListView.addItemDecoration(itemDecoration);
        placesListView.addOnScrollListener(mEndlessScrollListener);
        placesListView.setAdapter(pizzaPlacesAdapter);

    }


    private void getQueryResults(int offset) {


        yahooAPI.getQueryResults("select * from local.search where zip='78759' and query='pizza'", "json", true, "")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> {
                    Log.d(TAG, error.toString());
                })
                .doOnNext(result -> {
                   placesList.addAll(result.getQueryInfo().getResults().getResults());
                   pizzaPlacesAdapter.notifyDataSetChanged();
                })
                .subscribe();
    }

}
