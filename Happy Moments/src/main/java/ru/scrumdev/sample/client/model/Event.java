package ru.scrumdev.sample.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//Вынести в пакет entiti
public class Event {
    private int id;
    private String description;
    private Date date;
    private String name;
    private String time;
    private ArrayList<Image> images;

    public Event() {
        this.id = 1;
        this.description = "Some Discription";
        this.date = new Date();
        this.name = "First Event";
        ArrayList<ru.scrumdev.sample.client.model.Image> list = new ArrayList<>();
        Image img = new Image(1, "C:\\GWT-NetCracker\\Happy Moments\\src\\main\\resources\\ru\\scrumdev\\sample\\1.png");
        list.add(img);
        this.images = list;
        this.time = "21.00";
    }

    public Event(int id, String description, Date date, String name, ArrayList<Image> images, String time) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}
