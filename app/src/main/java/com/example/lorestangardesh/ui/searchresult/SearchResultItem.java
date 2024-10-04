package com.example.lorestangardesh.ui.searchresult;

public class SearchResultItem {
    int imageId;
    String title;
    String supportText;
    String serviceStatus;
    public SearchResultItem(int imageId,
                            String title,
                            String supportText,
                            String serviceStatus) {
        this.imageId = imageId;
        this.title = title;
        this.supportText = supportText;
        this.serviceStatus = serviceStatus;
    }
}
