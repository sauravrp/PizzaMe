package com.example.sauravrp.pizzame.views.models;

import java.io.Serializable;

public class ListingsUiModel implements Serializable {

    private String id;

    private String title;

    private ListingsAddressUiModel address;

    private String phone;

    private String distance;


    public ListingsUiModel(String id, String title, String address, String city, String state, String phone, String distance) {
        this.id = id;
        this.title = title;
        this.address = new ListingsAddressUiModel(address, city, state);
        this.phone = phone;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public ListingsAddressUiModel getAddress() {
        return address;
    }

    public void setAddress(ListingsAddressUiModel address) {
        this.address = address;
    }
}
