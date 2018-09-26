package com.example.sauravrp.pizzame.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.example.sauravrp.pizzame.views.models.ListingsUiModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ListingDetailViewModel extends ViewModel {

    private final PublishSubject<String> phoneNumberSelected = PublishSubject.create();
    private final PublishSubject<ListingsUiModel> addressSelected = PublishSubject.create();

    public void callPhoneNumber(String number) {
        phoneNumberSelected.onNext(number);
    }

    public void gotoAddress(ListingsUiModel address) {
        addressSelected.onNext(address);
    }

    public Observable<String> getSelectedPhoneNumber() {
        return phoneNumberSelected;
    }

    public Observable<ListingsUiModel> getSelectedAddress() {
        return addressSelected;
    }
}
