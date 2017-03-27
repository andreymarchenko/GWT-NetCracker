package ru.happyMoments.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.happyMoments.client.entity.Image;

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
    private List<ImageDto> images;

    public EventDto() {
        this.id = 1;
        this.description = "Some description";
        this.date = new Date();
        this.name = "First Event";
        List<ImageDto> list = new ArrayList<>();
        ImageDto img = new ImageDto(1, "http://www.google.com/images/logo.gif");
        list.add(img);
        this.images = list;
        this.time = "21.00";
    }

    public EventDto(@JsonProperty("id") int id,
                    @JsonProperty("description") String description,
                    @JsonProperty("date") Date date,
                    @JsonProperty("name") String name,
                    @JsonProperty("images") List<ImageDto> images,
                    @JsonProperty("time") String time) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.name = name;
        this.images = images;
        this.time = time;
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
                ", images=" + images +
                '}';
    }

    public List<ImageDto> getImages() {
        return images;
    }
}
