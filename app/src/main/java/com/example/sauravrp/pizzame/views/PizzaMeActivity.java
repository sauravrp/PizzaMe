package com.example.sauravrp.pizzame.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sauravrp.pizzame.R;
import com.example.sauravrp.pizzame.models.Result;
import com.example.sauravrp.pizzame.viewmodels.PizzaMeViewModel;
import com.example.sauravrp.pizzame.views.adapters.PizzaPlacesAdapter;
import com.example.sauravrp.pizzame.views.viewhelpers.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

public class PizzaMeActivity extends AppCompatActivity {

    private final String TAG = "PizzaMeActivity";

    @Inject
    PizzaMeViewModel pizzaMeViewModel;

    @BindView(R.id.list_view)
    RecyclerView placesListView;

    @BindView(R.id.error_text)
    TextView errorText;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private CompositeDisposable compositeDisposable;

    private PizzaPlacesAdapter pizzaPlacesAdapter;
    private ArrayList<Result> placesList = new ArrayList<>();
    private EndlessRecyclerViewScrollListener endlessScrollListener;

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
        bind();
        endlessScrollListener.resetState();
        placesList.clear();
        pizzaPlacesAdapter.notifyDataSetChanged();
        showProgress(true);
        showErrorText(false);
        showPlacesListView(false);
        pizzaMeViewModel.offsetSelected(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBind();
    }

    private void bind() {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(pizzaMeViewModel.getResults()
                .subscribe(this::showResults, this::showError));
    }

    private void unBind() {
        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    private void setupRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        pizzaPlacesAdapter = new PizzaPlacesAdapter(PizzaMeActivity.this, placesList);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                pizzaMeViewModel.offsetSelected(totalItemsCount);
            }
        };
        placesListView.setLayoutManager(layoutManager);
        placesListView.addItemDecoration(itemDecoration);
        placesListView.addOnScrollListener(endlessScrollListener);
        placesListView.setAdapter(pizzaPlacesAdapter);

    }

    private void showResults(List<Result> resultList) {
        placesList.addAll(resultList);
        pizzaPlacesAdapter.notifyDataSetChanged();
        showProgress(false);
        showErrorText(false);
        showPlacesListView(true);
    }

    private void showError(Throwable error) {
        Log.d(TAG, error.toString());
        showProgress(false);
        showErrorText(true);
        showPlacesListView(false);
    }

    private void showErrorText(boolean visible) {
        errorText.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void showProgress(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void showPlacesListView(boolean visible) {
        placesListView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
