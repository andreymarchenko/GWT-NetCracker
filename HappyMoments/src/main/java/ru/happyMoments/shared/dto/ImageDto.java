package ru.happyMoments.shared.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ImageDto implements Serializable{

    private int id;
    private String url;

    public ImageDto() {
        id = 0;
        url = "";
    }

    @JsonCreator
    public ImageDto(@JsonProperty("id") int id,
                    @JsonProperty("url") String url){
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageDto imageDto = (ImageDto) o;

        if (id != imageDto.id) return false;
        return url.equals(imageDto.url);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + url.hashCode();
        return result;
    }
}
