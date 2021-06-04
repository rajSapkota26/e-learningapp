package com.raj.eLearning.model;

public class ResourcesModel {
    private int id;
    private String title;
    private String link;

    public ResourcesModel(int id, String title, String li) {
        this.id = id;
        this.title = title;
        this.link = li;
    }

    public ResourcesModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
