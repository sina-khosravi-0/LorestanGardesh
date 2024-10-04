package com.example.lorestangardesh.ui.hotel;

public class HotelReservationItem {
    public int imageId;
    public String hotelName;
    public String star;
    public String rating;
    public String city;
    public String location;
    public String beds;
    public String remainingWarning;
    public String price;

    public HotelReservationItem(int imageId,
                                String hotelName,
                                String star,
                                String rating,
                                String city,
                                String location,
                                String beds,
                                String remainingWarning,
                                String price) {
        this.imageId = imageId;
        this.hotelName = hotelName;
        this.star = star;
        this.rating = rating;
        this.city = city;
        this.location = location;
        this.beds = beds;
        this.remainingWarning = remainingWarning;
        this.price = price;
    }
}
