package com.example.lorestangardesh.db;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Location {
    private String id;
    private List<String> photos;
    private List<BriefLocation> nearestLocations;
    private String title;
    private String address;
    private String phone;
    private double lat;
    private double lon;
    private String locationType;
    private String description;
    private String helperDescription;
    private boolean alwaysOpen;
    private List<OpeningHour> openingHours;

    public Location() {
        photos = new ArrayList<>();
        nearestLocations = new ArrayList<>();
        openingHours = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addPhoto(String photo) {
        this.photos.add(photo);
    }

    public void addNearestLocation(BriefLocation nearestLocation) {
        this.nearestLocations.add(nearestLocation);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHelperDescription(String helperDescription) {
        this.helperDescription = helperDescription;
    }

    public void setAlwaysOpen(boolean alwaysOpen) {
        this.alwaysOpen = alwaysOpen;
    }

    public void addOpeningHour(OpeningHour openingHour) {
        this.openingHours.add(openingHour);
    }

    public String getId() {
        return id;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public List<BriefLocation> getNearestLocations() {
        return nearestLocations;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getDescription() {
        return description;
    }

    public String getHelperDescription() {
        return helperDescription;
    }

    public boolean isAlwaysOpen() {
        return alwaysOpen;
    }

    public List<OpeningHour> getOpeningHours() {
        return openingHours;
    }
}
