package com.todor.yalantistask.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CurrentUser extends RealmObject {

    private String name;

    @PrimaryKey
    private String email;
    private String profileIcon;
    private String birthday;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(String icon) {
        this.profileIcon = icon;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profileIcon='" + profileIcon + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }

}
