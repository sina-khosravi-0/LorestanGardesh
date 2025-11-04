package com.example.lorestangardesh.ui.searchresult;

public class SearchResultItem {
    int imageId;
    String photo;
    String title;
    String supportText;
    String distance;
    public SearchResultItem(int imageId,
                            String title,
                            String supportText,
                            String distance) {
        this.imageId = imageId;
        this.title = title;
        this.supportText = supportText;
        this.distance = distance;
    }

    public SearchResultItem(String photo,
                            String title,
                            String supportText,
                            String distance) {
        this.photo = photo;
        this.title = title;
        this.supportText = supportText;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "SearchResultItem{" +
                "imageId=" + imageId +
                ", photo='" + photo + '\'' +
                ", title='" + title + '\'' +
                ", supportText='" + supportText + '\'' +
                ", serviceStatus='" + distance + '\'' +
                '}';
    }
}
