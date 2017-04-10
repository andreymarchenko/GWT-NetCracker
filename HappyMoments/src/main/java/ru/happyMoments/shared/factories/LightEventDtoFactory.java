package ru.happyMoments.shared.factories;

import ru.happyMoments.shared.dto.LightEventDto;

public class LightEventDtoFactory {
    public static LightEventDto create(double latitude, double longitude) {
        return new LightEventDto(latitude, longitude);
    }
}
