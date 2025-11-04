package com.example.lorestangardesh.ui.carousel;

import static com.example.lorestangardesh.statics.Constants.API_ADDRESS;

import android.graphics.Bitmap;

import com.example.lorestangardesh.statics.Constants;

public class CarouselItem {
    public int imageId;
    public String imagePath;
    public String title;
    public String description;

    public CarouselItem(String imagePath, String title, String description) {
        this.imagePath = imagePath;
        this.title = title;
        this.description = description;
    }

    public CarouselItem(int imageId, String title, String description) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
    }
}
