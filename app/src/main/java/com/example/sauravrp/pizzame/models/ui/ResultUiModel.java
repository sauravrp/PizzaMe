package com.example.sauravrp.pizzame.models.ui;

public class ResultUiModel {
    private String id;

    private String title;

     private String address;

    private String city;

    private String state;

    private String phone;

    private String distance;


    public ResultUiModel(String id, String title, String address, String city, String state, String phone, String distance) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.city = city;
        this.state = state;
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
}
