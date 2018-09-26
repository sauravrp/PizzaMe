package com.example.sauravrp.pizzame.views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.example.sauravrp.pizzame.helpers.IntentHelper;
import com.example.sauravrp.pizzame.views.models.ListingsUiModel;
import com.example.sauravrp.pizzame.viewmodels.ListingsViewModel;
import com.example.sauravrp.pizzame.views.adapters.ListingsAdapter;
import com.example.sauravrp.pizzame.views.viewhelpers.EndlessRecyclerViewScrollListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

public class ListingsActivity extends AppCompatActivity {

    private final String TAG = "ListingsActivity";

    private final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 1;

    @Inject
    ListingsViewModel listingsViewModel;

    @BindView(R.id.list_view)
    RecyclerView placesListView;

    @BindView(R.id.error_text)
    TextView errorText;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private CompositeDisposable compositeDisposable;

    private ListingsAdapter listingsAdapter;
    private ArrayList<ListingsUiModel> placesList = new ArrayList<>();
    private EndlessRecyclerViewScrollListener endlessScrollListener;

    private FusedLocationProviderClient mFusedLocationClient;
    private AlertDialog mLocationPreferredDialog = null;
    private boolean mIsLocationPreferredDialogVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupRecyclerView();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            getLocation();
        } else {
            // ask for permission
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                if(!mIsLocationPreferredDialogVisible) {
                    showLocationPreferredDialog();
                }
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_COARSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    // Logic to handle location object
                    Log.d(TAG, "last known location found");
                }
            }
        });
    }


    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    // TODO: Put this dialog in a central location (also created in ChangeLocationActivity and BaseLocationFragment)
    private void showLocationPreferredDialog() {

        mIsLocationPreferredDialogVisible = true;

        if (mLocationPreferredDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_PopupOverlay);

            mLocationPreferredDialog = builder.setMessage(R.string.message_enable_location_services_body_long)
                     .setCancelable(true)
                    .setPositiveButton(R.string.button_label_continue, (dialog, which) -> {
                        mIsLocationPreferredDialogVisible = false;
                        dialog.cancel();
                    })
                    .setNegativeButton(R.string.button_label_settings, (dialog, which) -> {
                        mIsLocationPreferredDialogVisible = false;
                        if (isDestroyed() || isFinishing()) {
                            return;
                        }
                        Intent intent = IntentHelper.getSettingsIntent(getPackageName());
                        startActivity(intent);
                    })
                    .setOnCancelListener(dialog -> {
                        if (isDestroyed() || isFinishing()) {
                            return;
                        }
                        mIsLocationPreferredDialogVisible = false;
                    })
                    .setTitle(R.string.message_enable_location_services_title)
                    .create();
        }

        mLocationPreferredDialog.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_COARSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    getLocation();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    if (!mIsLocationPreferredDialogVisible) {
                        showLocationPreferredDialog();
                    }
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        bind();

        endlessScrollListener.resetState();
        placesList.clear();
        listingsAdapter.notifyDataSetChanged();
        showProgress(true);
        showErrorText(false);
        showPlacesListView(false);
        listingsViewModel.getMoreListings(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBind();
    }

    private void bind() {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(listingsViewModel.getListings()
                .subscribe(this::showResults, this::showError));
        compositeDisposable.add(listingsViewModel.getSelectedListing()
                .subscribe(this::resultSelected));
    }

    private void unBind() {
        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        listingsAdapter = new ListingsAdapter(listingsViewModel, placesList);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                listingsViewModel.getMoreListings(totalItemsCount);
            }
        };
        placesListView.setLayoutManager(layoutManager);
        placesListView.addItemDecoration(itemDecoration);
        placesListView.addOnScrollListener(endlessScrollListener);
        placesListView.setAdapter(listingsAdapter);

    }

    private void showResults(List<ListingsUiModel> resultList) {
        if(resultList.size() == 0)
            return;
        placesList.addAll(resultList);
        listingsAdapter.notifyDataSetChanged();
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

    private void resultSelected(ListingsUiModel selection) {
        ListingDetailActivity.startActivity(this, selection);
    }

}
