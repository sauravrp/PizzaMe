package com.example.sauravrp.pizzame.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.sauravrp.pizzame.R;
import com.example.sauravrp.pizzame.network.YahooAPI;
import com.example.sauravrp.pizzame.viewmodels.PizzaMeViewModel;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PizzaMeActivity extends AppCompatActivity {

    private final String TAG = "PizzaMeActivity";

//    @Inject
//    PizzaMeViewModel pizzaMeViewModel;

    @Inject
    YahooAPI yahooAPI;

    @OnClick(R.id.temp)
    public void clicked() {
        yahooAPI.getQueryResults("select * from local.search where zip='78759' and query='pizza'", "json", true, "")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> {
                    Log.d(TAG, error.toString());
                })
                .doOnNext(result -> {
                    Log.d("TAG", result.toString());
                })
                .subscribe();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.pizza_me_activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





    }

}
