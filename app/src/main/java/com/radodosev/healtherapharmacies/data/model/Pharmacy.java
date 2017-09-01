package com.radodosev.healtherapharmacies.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rado on 8/24/2017.
 */

public class Pharmacy implements Serializable {
    private static final long serialVersionUID = -107557847858738581L;

    @SerializedName("place_id")
    private String placeId;
    @SerializedName("place_title")
    private String placeTitle;
    @SerializedName("place_type")
    private String placeType;
    @SerializedName("long")
    private String longitude;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("current_distance")
    private String currentDistance;


    public Pharmacy(String placeId, String placeTitle, String placeType, String longitude, String latitude, String currentDistance) {
        this.placeId = placeId;
        this.placeTitle = placeTitle;
        this.placeType = placeType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.currentDistance = currentDistance;
    }

    public String getCurrentDistance() {
        return currentDistance;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public String getPlaceType() {
        return placeType;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
