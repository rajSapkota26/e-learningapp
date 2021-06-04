package com.raj.eLearning.model;

public class VideoLectureModel {
    private int id;
    private String title;
    private String videoUrl;
    private  String coverImage;

    public VideoLectureModel() {
    }

    public VideoLectureModel(int id, String title, String videoUrl, String coverImage) {
        this.id = id;
        this.title = title;
        this.videoUrl = videoUrl;
        this.coverImage = coverImage;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
