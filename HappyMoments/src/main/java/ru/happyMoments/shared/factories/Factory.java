package ru.happyMoments.shared.factories;

import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;
import ru.happyMoments.shared.dto.LightEventDto;

public class Factory {

    public static EventDto createEventDto(int id,
                                  String description,
                                  String date,
                                  String name,
                                  ImageDto image,
                                  String time,
                                  double latitude,
                                  double longitude) {
        return new EventDto(id, description, date, name, image, time, latitude, longitude);
    }

    public static ImageDto createImageDto(int id, String url) {
        return new ImageDto(id, url);
    }

    public static LightEventDto createLightEventDto(double latitude, double longitude) {
        return new LightEventDto(latitude, longitude);
    }


}