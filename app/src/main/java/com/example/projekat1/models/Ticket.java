package com.example.projekat1.models;

public class Ticket {

    private String type;
    private String priority;
    private String estimated;
    private String title;
    private String description;
    private String progress;

    public Ticket(String type, String priority, String estimated, String title, String description) {
        this.type = type;
        this.priority = priority;
        this.estimated = estimated;
        this.title = title;
        this.description = description;
        this.progress = "todo";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getEstimated() {
        return estimated;
    }

    public void setEstimated(String estimated) {
        this.estimated = estimated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
