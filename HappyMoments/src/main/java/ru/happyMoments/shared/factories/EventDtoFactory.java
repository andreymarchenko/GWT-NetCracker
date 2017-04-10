package ru.happyMoments.shared.factories;

import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;

public class EventDtoFactory {
    public static EventDto create(int id,
                                          String description,
                                          String date,
                                          String name,
                                          ImageDto image,
                                          String time,
                                          double latitude,
                                          double longitude) {
        return new EventDto(id, description, date, name, image, time, latitude, longitude);
    }
}