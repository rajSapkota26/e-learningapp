package com.raj.eLearning.model;

public class ArticlesModel {
    private int id;
    private String title;
    private String imageUrl;
    private  String desc;
    private  String articleLink;

    public ArticlesModel() {
    }

    public ArticlesModel(int id, String title, String imageUrl, String desc, String articleLink) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.articleLink = articleLink;
    }

    public ArticlesModel(int id, String title, String imageUrl, String desc) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.desc = desc;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
