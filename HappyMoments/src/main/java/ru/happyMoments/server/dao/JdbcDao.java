package ru.happyMoments.server.dao;

import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

import java.io.File;
import java.util.List;

public interface JdbcDao {

    List<LightEventDto> loadAllEvents();

    EventDto getEventByLatLng(LightEventDto lightEventDto);

    void createEvent(EventDto eventDto);

    void editEvent(EventDto eventDto);

    void deleteEvent(EventDto eventDto);

    void upload(File file);
}
