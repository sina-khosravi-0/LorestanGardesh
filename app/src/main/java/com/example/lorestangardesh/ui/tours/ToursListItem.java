package com.example.lorestangardesh.ui.tours;

public class ToursListItem {
    public int imageId;
    public String tourName;
    public String travelAgency;
    public String tourLocation;
    public String tourType;
    public String transportType;
    public String departureDateTime;
    public String duration;
    public String price;

    public ToursListItem(int imageId,
                         String tourName,
                         String travelAgency,
                         String tourLocation,
                         String tourType,
                         String transportType,
                         String departureDateTime,
                         String duration,
                         String price) {
        this.imageId = imageId;
        this.tourName = tourName;
        this.travelAgency = travelAgency;
        this.tourLocation = tourLocation;
        this.tourType = tourType;
        this.transportType = transportType;
        this.departureDateTime = departureDateTime;
        this.duration = duration;
        this.price = price;
    }
}
