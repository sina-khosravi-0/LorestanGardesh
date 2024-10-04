package com.example.lorestangardesh.ui.flight;

import android.widget.TextView;

public class FlightTicketItem {
    public String agency;
    public String sourceLocation;
    public String departureTime;
    public String arrivalLocation;
    public String arrivalTime;
    public String price;
    public String ticketType;

    public FlightTicketItem(String agency,
                            String sourceLocation,
                            String departureTime,
                            String arrivalLocation,
                            String arrivalTime,
                            String price,
                            String ticketType) {
        this.agency = agency;
        this.sourceLocation = sourceLocation;
        this.departureTime = departureTime;
        this.arrivalLocation = arrivalLocation;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.ticketType = ticketType;
    }
}
