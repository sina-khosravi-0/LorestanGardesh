package com.example.lorestangardesh.ui.searchresult;

public class SearchResultItem {
    int imageId;
    String title;
    String supportText;
    String openState;
    public SearchResultItem(int imageId,
                            String title,
                            String supportText,
                            String openState) {
        this.imageId = imageId;
        this.title = title;
        this.supportText = supportText;
        this.openState = openState;
    }
}
