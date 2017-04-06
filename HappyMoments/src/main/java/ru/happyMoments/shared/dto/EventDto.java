package ru.happyMoments.shared.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDto implements Serializable{
    private int id;
    private String description;
    private String date;
    private String name;
    private String time;
    private ImageDto image;
    private double latitude;
    private double longitude;

    public EventDto() {

    }

    @JsonCreator
    public EventDto(@JsonProperty("id") int id,
                    @JsonProperty("description") String description,
                    @JsonProperty("date") String date,
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventDto eventDto = (EventDto) o;

        if (id != eventDto.id) return false;
        if (Double.compare(eventDto.latitude, latitude) != 0) return false;
        if (Double.compare(eventDto.longitude, longitude) != 0) return false;
        if (description != null ? !description.equals(eventDto.description) : eventDto.description != null)
            return false;
        if (date != null ? !date.equals(eventDto.date) : eventDto.date != null) return false;
        if (name != null ? !name.equals(eventDto.name) : eventDto.name != null) return false;
        if (time != null ? !time.equals(eventDto.time) : eventDto.time != null) return false;
        return image != null ? image.equals(eventDto.image) : eventDto.image == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
