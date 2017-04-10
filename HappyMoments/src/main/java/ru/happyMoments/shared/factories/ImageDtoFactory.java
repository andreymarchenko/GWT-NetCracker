package ru.happyMoments.shared.factories;

import ru.happyMoments.shared.dto.ImageDto;

public class ImageDtoFactory {
    public static ImageDto create(int id, String url) {
        return new ImageDto(id, url);
    }
}
