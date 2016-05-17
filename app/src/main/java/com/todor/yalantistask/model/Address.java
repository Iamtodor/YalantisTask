package com.todor.yalantistask.model;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("id")
    private Integer id;

    @SerializedName("district")
    private District district;

    @SerializedName("city")
    private City city;

    @SerializedName("street")
    private Street street;

    @SerializedName("house")
    private House house;

    @SerializedName("flat")
    private String flat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", district=" + district +
                ", city=" + city +
                ", street=" + street +
                ", house=" + house +
                ", flat='" + flat + '\'' +
                '}';
    }
}
