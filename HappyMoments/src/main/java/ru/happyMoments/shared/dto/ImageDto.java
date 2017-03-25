package ru.happyMoments.shared.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ImageDto implements Serializable{

    private int id;
    private String url;

    public ImageDto() {
    }

    @JsonCreator
    public ImageDto(@JsonProperty("id") int id,
                    @JsonProperty("url") String url){
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
