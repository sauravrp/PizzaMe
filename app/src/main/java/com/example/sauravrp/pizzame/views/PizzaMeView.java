package com.example.sauravrp.pizzame.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.sauravrp.pizzame.R;
import com.example.sauravrp.pizzame.viewmodels.PizzaMeViewModel;

import javax.inject.Inject;

public class PizzaMeView extends AppCompatActivity {

    @Inject
    PizzaMeViewModel pizzaMeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_me_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
