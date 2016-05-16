package com.todor.yalantistask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Performer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("person")
    @Expose
    private String person;
    @SerializedName("deadline")
    @Expose
    private Integer deadline;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

}
