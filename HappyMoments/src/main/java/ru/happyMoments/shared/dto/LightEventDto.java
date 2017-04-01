package ru.happyMoments.shared.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LightEventDto implements Serializable {

    private double latitude;
    private double longitude;

    public LightEventDto() {

    }

    @JsonCreator
    public LightEventDto(@JsonProperty("latitude")double latitude,
                         @JsonProperty("longitude")double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
