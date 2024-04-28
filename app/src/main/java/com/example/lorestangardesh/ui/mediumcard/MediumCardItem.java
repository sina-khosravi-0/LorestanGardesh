package com.example.lorestangardesh.ui.mediumcard;

import android.graphics.Bitmap;

public class MediumCardItem {
    public int imageId;
    public Bitmap image;
    public String title;
    public String description;

    public MediumCardItem(Bitmap image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }
    public MediumCardItem(int imageId, String title, String description) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
    }
}
