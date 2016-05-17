package com.todor.yalantistask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ticket {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("user")
    @Expose
    private Author author;

    @SerializedName("category")
    @Expose
    private Category category;

    @SerializedName("type")
    @Expose
    private Type type;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("created_date")
    @Expose
    private String createdDate;

    @SerializedName("state")
    @Expose
    private State state;

    @SerializedName("ticket_id")
    @Expose
    private String ticketId;

//    @SerializedName("files")
//    @Expose
//    private String files;

    @SerializedName("performers")
    @Expose
    private List<Performer> performers;

    @SerializedName("deadline")
    @Expose
    private String deadline;

    @SerializedName("likes_counter")
    @Expose
    private String likesCounter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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

//    public String getFiles() {
//        return files;
//    }
//
//    public void String(String files) {
//        this.files = files;
//    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getLikesCounter() {
        return likesCounter;
    }

    public void setLikesCounter(String likesCounter) {
        this.likesCounter = likesCounter;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", state=" + state +
                ", ticketId='" + ticketId + '\'' +
                ", performers=" + performers +
                ", deadline='" + deadline + '\'' +
                ", likesCounter='" + likesCounter + '\'' +
                '}';
    }
}
