package com.todor.yalantistask.model;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class User extends RealmObject{

    private String name;
    private String email;
    private String icon;
    private String birthday;

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
        return icon;
    }

    public void setProfileIcon(String icon) {
        this.icon = icon;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
