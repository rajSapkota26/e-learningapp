package com.raj.eLearning.model;

public class EBookModel {
    private int id;
    private String bookName;
    private String downloadLink;
    private  String imageUrl;

    public EBookModel() {
    }

    public EBookModel(int id, String bookName, String downloadLink, String imageUrl) {
        this.id = id;
        this.bookName = bookName;
        this.downloadLink = downloadLink;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
