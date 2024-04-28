package com.example.lorestangardesh.ui.carousel;

import android.graphics.Bitmap;

public class CarouselItem {
    public int imageId;
    public Bitmap image;
    public String title;
    public String description;

    public CarouselItem(Bitmap image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public CarouselItem(int imageId, String title, String description) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
    }
}
