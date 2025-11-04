package com.example.lorestangardesh.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static final List<PromptObject> ASSISTANT_CONVERSATION_LIST = new ArrayList<>();
    private static DataManager dataManager;
    private List<Location> allLocationList;
    private List<Location> searchedLocationList;
    private List<GalleryItem> allPhotos;


    private DataManager() {
    }

    public static synchronized DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public void parseAllLocation(JSONArray data) {
        allLocationList = new ArrayList<>();
        allPhotos = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            Location location = null;
            try {
                location = new Location();
                JSONObject loc = data.getJSONObject(i);
                location.setId(loc.getString("id"));
                location.setTitle(loc.getString("title"));
                location.setAddress(loc.getString("address"));
                location.setPhone(loc.getString("phone"));
                location.setLat(loc.getDouble("lat"));
                location.setLon(loc.getDouble("lon"));
                location.setLocationType(loc.getString("location_type"));
                location.setDescription(loc.getString("description"));
                location.setHelperDescription(loc.getString("helper_description"));
                location.setAlwaysOpen(loc.getBoolean("always_open"));
                JSONArray photos = loc.getJSONArray("photos");
                for (int j = 0; j < photos.length(); j++) {
                    String photo = photos.getJSONObject(j).getString("photo");
                    location.addPhoto(photo);
                    allPhotos.add(new GalleryItem(photo, location.getTitle(), location.getLocationType()));
                }
                JSONArray openingHours = loc.getJSONArray("opening_hours");
                for (int j = 0; j < openingHours.length(); j++) {
                    JSONObject openingHourObj = openingHours.getJSONObject(j);
                    location.addOpeningHour(new OpeningHour(
                            openingHourObj.getInt("day"),
                            openingHourObj.getString("open_time"),
                            openingHourObj.getString("close_time")
                    ));
                }
                JSONArray nearestLocations = loc.getJSONArray("nearest_locations");

                for (int j = 0; j < nearestLocations.length(); j++) {
                    JSONObject nearestLocationsObj = nearestLocations.getJSONObject(j);
                    location.addNearestLocation(new BriefLocation(
                            nearestLocationsObj.getString("id"),
                            nearestLocationsObj.getString("title"),
                            nearestLocationsObj.getDouble("distance_km")
                    ));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            allLocationList.add(location);
        }
    }

    public void parseSearchedLocation(JSONArray data) {
        searchedLocationList = new ArrayList<>();
        allPhotos = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            Location location = null;
            try {
                location = new Location();
                JSONObject loc = data.getJSONObject(i);
                location.setId(loc.getString("id"));
                location.setTitle(loc.getString("title"));
                location.setAddress(loc.getString("address"));
                location.setPhone(loc.getString("phone"));
                location.setLat(loc.getDouble("lat"));
                location.setLon(loc.getDouble("lon"));
                location.setLocationType(loc.getString("location_type"));
                location.setDescription(loc.getString("description"));
                location.setHelperDescription(loc.getString("helper_description"));
                location.setAlwaysOpen(loc.getBoolean("always_open"));
                JSONArray photos = loc.getJSONArray("photos");
                for (int j = 0; j < photos.length(); j++) {
                    String photo = photos.getJSONObject(j).getString("photo");
                    location.addPhoto(photo);
                    allPhotos.add(new GalleryItem(photo, location.getTitle(), location.getLocationType()));
                }
                JSONArray openingHours = loc.getJSONArray("opening_hours");
                for (int j = 0; j < openingHours.length(); j++) {
                    JSONObject openingHourObj = openingHours.getJSONObject(j);
                    location.addOpeningHour(new OpeningHour(
                            openingHourObj.getInt("day"),
                            openingHourObj.getString("open_time"),
                            openingHourObj.getString("close_time")
                    ));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            searchedLocationList.add(location);
        }
    }

    public Location getLocationDetails(int id) {
        JSONObject locJson = DatabaseHandlerSingleton.getInstance(null).getLocationDetails(id);
        Location location = new Location();
        try {
            location.setId(locJson.getString("id"));
            location.setTitle(locJson.getString("title"));
            location.setAddress(locJson.getString("address"));
            location.setPhone(locJson.getString("phone"));
            location.setLat(locJson.getDouble("lat"));
            location.setLon(locJson.getDouble("lon"));
            location.setLocationType(locJson.getString("location_type"));
            location.setDescription(locJson.getString("description"));
            location.setHelperDescription(locJson.getString("helper_description"));
            location.setAlwaysOpen(locJson.getBoolean("always_open"));



            JSONArray photos = locJson.getJSONArray("photos");
            for (int j = 0; j < photos.length(); j++) {
                String photo = photos.getJSONObject(j).getString("photo");
                location.addPhoto(photo);
                allPhotos.add(new GalleryItem(photo, location.getTitle(), location.getLocationType()));
            }
            JSONArray openingHours = locJson.getJSONArray("opening_hours");
            for (int j = 0; j < openingHours.length(); j++) {
                JSONObject openingHourObj = openingHours.getJSONObject(j);
                location.addOpeningHour(new OpeningHour(
                        openingHourObj.getInt("day"),
                        openingHourObj.getString("open_time"),
                        openingHourObj.getString("close_time")
                ));
            }
            JSONArray nearestLocations = locJson.getJSONArray("nearest_locations");
            for (int j = 0; j < nearestLocations.length(); j++) {
                JSONObject nearestLocationsObj = nearestLocations.getJSONObject(j);
                location.addNearestLocation(new BriefLocation(
                        nearestLocationsObj.getString("id"),
                        nearestLocationsObj.getString("title"),
                        nearestLocationsObj.getDouble("distance_km")
                ));
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return location;
    }

    public void fetchAllLocations() throws JSONException {
        JSONArray allLocations = DatabaseHandlerSingleton.getInstance(null).getAllLocations();
        parseAllLocation(allLocations);
    }

    public List<Location> getAllLocations() {
        return allLocationList;
    }

    public List<GalleryItem> getAllPhotos() {
        return allPhotos;
    }

//    public List<Location> getSearchResults(String searchTerm, String locationType) {
//        JSONArray results = DatabaseHandlerSingleton.getInstance(null).getSearchResults(searchTerm, locationType);
//        parseSearchedLocation(results);
//        return searchedLocationList;
//    }

    public List<Location> getSearchResults(String searchTerm) {
        JSONArray results = DatabaseHandlerSingleton.getInstance(null).getSearchResults(searchTerm, "");
        parseSearchedLocation(results);
        return searchedLocationList;
    }

    public List<Location> getNearLocations(int km, double lat, double lon) {
        JSONArray results = DatabaseHandlerSingleton.getInstance(null).getNearLocations(km, lat, lon);
        parseSearchedLocation(results);
        return searchedLocationList;
    }

    public String getAssistantResponse() throws JSONException {
        JSONObject response = DatabaseHandlerSingleton.getInstance(null).sendPrompt();
        return response.getString("content");
    }

}
