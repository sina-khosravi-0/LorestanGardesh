package com.example.lorestangardesh.db;

public class BriefLocation {
    public String id;
    public String title;
    public double distance_km;

    public BriefLocation(String id, String title, double distance_km) {
        this.id = id;
        this.title = title;
        this.distance_km = distance_km;
    }
}
