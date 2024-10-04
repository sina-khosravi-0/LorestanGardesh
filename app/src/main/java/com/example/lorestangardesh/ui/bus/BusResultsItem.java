package com.example.lorestangardesh.ui.bus;

public class BusResultsItem {

    public String agency;
    public String sourceLocation;
    public String departureTime;
    public String arrivalLocation;
    public String price;
    public String vip;

    public BusResultsItem(String agency,
                            String sourceLocation,
                            String departureTime,
                            String arrivalLocation,
                            String price,
                            String vip) {
        this.agency = agency;
        this.sourceLocation = sourceLocation;
        this.departureTime = departureTime;
        this.arrivalLocation = arrivalLocation;
        this.price = price;
        this.vip = vip;
    }
}
