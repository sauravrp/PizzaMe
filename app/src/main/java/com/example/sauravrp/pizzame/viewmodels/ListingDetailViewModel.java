package com.example.sauravrp.pizzame.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.sauravrp.pizzame.views.models.ListingsUiModel;

public class ListingDetailViewModel extends ViewModel {

    private ListingsUiModel selection;

    private final MutableLiveData<String> phoneNumberSelected = new MutableLiveData<>();

    private final MutableLiveData<ListingsUiModel> addressSelected = new MutableLiveData<>();

    public void callPhoneNumber(String number) {
        phoneNumberSelected.setValue(number);
    }

    public void gotoAddress(ListingsUiModel address) {
        addressSelected.setValue(address);
    }

    public LiveData<String> getSelectedPhoneNumber() {
        return phoneNumberSelected;
    }

    public LiveData<ListingsUiModel> getSelectedAddress() {
        return addressSelected;
    }

    public ListingsUiModel getSelection() {
        return selection;
    }

    public void setSelection(ListingsUiModel selection) {
        this.selection = selection;
    }
}
