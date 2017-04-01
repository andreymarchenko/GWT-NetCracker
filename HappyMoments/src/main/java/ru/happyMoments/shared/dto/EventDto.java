package ru.happyMoments.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDto implements Serializable{
    private int id;
    private String description;
    private Date date;
    private String name;
    private String time;
    private ImageDto image;
    private double latitude;
    private double longitude;

    public EventDto() {

    }

    public EventDto(@JsonProperty("id") int id,
                    @JsonProperty("description") String description,
                    @JsonProperty("date") Date date,
                    @JsonProperty("name") String name,
                    @JsonProperty("image") ImageDto image,
                    @JsonProperty("time") String time,
                    @JsonProperty("latitude") double latitude,
                    @JsonProperty("longitude") double longitude) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.name = name;
        this.image = image;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", images=" + image +
                '}';
    }

    public ImageDto getImage() {
        return image;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
