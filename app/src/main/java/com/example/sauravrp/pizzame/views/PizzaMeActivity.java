package com.example.sauravrp.pizzame.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.sauravrp.pizzame.R;
import com.example.sauravrp.pizzame.network.YahooAPI;
import com.example.sauravrp.pizzame.viewmodels.PizzaMeViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PizzaMeActivity extends AppCompatActivity {

    private final String TAG = "PizzaMeActivity";

//    @Inject
//    PizzaMeViewModel pizzaMeViewModel;

    @Inject
    YahooAPI yahooAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.pizza_me_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        yahooAPI.getQueryResults("select * from local.search where zip='78759' and query='pizza'&format=json&diagnostics=true&callback=")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> {
                    Log.d("TAG", result.toString());
                });


    }

}
