package com.example.lorestangardesh.ui.mediumcard;

public class MediumCardItem {
    public int imageId;
    public String photo;
    public String title;
    public String description;

    public MediumCardItem(String photo, String title, String description) {
        this.photo = photo;
        this.title = title;
        this.description = description;
    }
    public MediumCardItem(int imageId, String title, String description) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
    }
}
