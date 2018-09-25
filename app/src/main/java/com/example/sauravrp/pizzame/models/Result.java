package com.example.sauravrp.pizzame.models;

import com.google.gson.annotations.SerializedName;

public class Result {
    private String id;

    @SerializedName("Title")
    private String title;

    @SerializedName("Address")
    private String address;

    @SerializedName("City")
    private String city;

    @SerializedName("State")
    private String state;

    @SerializedName("Phone")
    private String phone;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
