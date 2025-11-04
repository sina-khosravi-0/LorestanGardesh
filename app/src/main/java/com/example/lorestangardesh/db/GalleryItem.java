package com.example.lorestangardesh.db;

public class GalleryItem {
    private String path;
    private String title;
    private String locationType;

    public GalleryItem(String path, String title, String locationType) {
        this.path = path;
        this.title = title;
        this.locationType = locationType;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationType() {
        return locationType;
    }
}
