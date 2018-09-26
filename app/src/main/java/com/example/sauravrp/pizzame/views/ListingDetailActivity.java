package com.example.sauravrp.pizzame.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.example.sauravrp.pizzame.BR;
import com.example.sauravrp.pizzame.R;
import com.example.sauravrp.pizzame.helpers.IntentHelper;
import com.example.sauravrp.pizzame.viewmodels.ListingDetailViewModel;
import com.example.sauravrp.pizzame.views.models.ListingsAddressUiModel;
import com.example.sauravrp.pizzame.views.models.ListingsUiModel;

import io.reactivex.disposables.CompositeDisposable;

public class ListingDetailActivity extends AppCompatActivity {

    private final static String SELECTION = "selection";

    public static void startActivity(Context ctx, ListingsUiModel selected) {
        Intent intent = new Intent(ctx, ListingDetailActivity.class);
        intent.putExtra(SELECTION, selected);
        ctx.startActivity(intent);
    }

    private ListingDetailViewModel viewModel;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_listing_detail);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(ListingDetailViewModel.class);
        binding.setVariable(BR.uiModel, getSelectionFromBundle());
        binding.setVariable(BR.viewModel, viewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private ListingsUiModel getSelectionFromBundle() {
        Bundle extras = getIntent().getExtras();
        ListingsUiModel selection = (ListingsUiModel) extras.getSerializable(SELECTION);
        return selection;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }



    @Override
    protected void onPause() {
        super.onPause();
        unBind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void bind() {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.getSelectedPhoneNumber()
                .subscribe(this::callPhoneNumber));
        compositeDisposable.add(viewModel.getSelectedAddress().subscribe(this::gotoAddress));
    }

    private void unBind() {
        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    private void callPhoneNumber(String number) {
        if(!TextUtils.isEmpty(number)) {
            IntentHelper.launchPhone(this, number);
        }
    }

    private void gotoAddress(ListingsUiModel data) {
        if(!TextUtils.isEmpty(data.getAddress().getStreet())
                && !TextUtils.isEmpty(data.getAddress().getCity())) {
            IntentHelper.launchMaps(this, data.getTitle(), data.getAddress().getStreet(), data.getAddress().getCity(), data.getAddress().getState());
        }
    }
}
