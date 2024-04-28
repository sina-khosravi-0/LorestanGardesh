package com.example.lorestangardesh.ui.review;

import android.graphics.Bitmap;

public class ReviewItem {
    public Bitmap profilePicture;
    public String username;
    public String text;
    public boolean isLiked;

    public ReviewItem(Bitmap profilePicture, String username, String text, boolean isLiked) {
        this.profilePicture = profilePicture;
        this.username = username;
        this.text = text;
        this.isLiked = isLiked;
    }
}
