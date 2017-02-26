package ru.scrumdev.sample.client;

import java.util.Date;
import java.util.List;

public class Event {
    private int id;
    private String description;
    private Date date;
    private String name;
    private List<Image> images;

    public Event() {
    }

    public Event(int id, String description, Date date, String name, List<Image> images) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.name = name;
        this.images = images;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
