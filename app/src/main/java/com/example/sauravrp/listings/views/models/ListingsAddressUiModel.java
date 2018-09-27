package com.example.sauravrp.listings.views.models;

import java.io.Serializable;

public class ListingsAddressUiModel implements Serializable {

    private String street;

    private String city;

    private String state;

    public ListingsAddressUiModel(String address, String city, String state) {
        this.street = address;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ListingsAddressUiModel{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
