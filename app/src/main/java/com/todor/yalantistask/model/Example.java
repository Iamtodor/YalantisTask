package com.todor.yalantistask.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

public class Example extends RealmObject {

    @SerializedName("id")
    private Integer id;

    @SerializedName("user")
    private User user;

    @SerializedName("category")
    private Category category;

    @SerializedName("type")
    private Type type;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("created_date")
    private Integer createdDate;

    @SerializedName("start_date")
    private Integer startDate;

    @SerializedName("state")
    private State state;

    @SerializedName("ticket_id")
    private String ticketId;

    @SerializedName("files")
    private List<File> files = new ArrayList<>();

    @SerializedName("performers")
    private List<Performer> performers = new ArrayList<>();

    @SerializedName("deadline")
    private Integer deadline;

    @SerializedName("likes_counter")
    private Integer likesCounter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Integer getLikesCounter() {
        return likesCounter;
    }

    public void setLikesCounter(Integer likesCounter) {
        this.likesCounter = likesCounter;
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                ", user=" + user +
                ", category=" + category +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdDate=" + createdDate +
                ", startDate=" + startDate +
                ", state=" + state +
                ", ticketId='" + ticketId + '\'' +
                ", files=" + files +
                ", performers=" + performers +
                ", deadline=" + deadline +
                ", likesCounter=" + likesCounter +
                '}';
    }
}
