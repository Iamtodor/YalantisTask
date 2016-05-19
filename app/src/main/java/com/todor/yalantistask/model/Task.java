package com.todor.yalantistask.model;

import io.realm.RealmObject;

public class Task extends RealmObject {

    private String imgUrl;
    private String likeValue;
    private String header;
    private String address;
    private String date;
    private String expiredTime;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(String likeValue) {
        this.likeValue = likeValue;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }
}
