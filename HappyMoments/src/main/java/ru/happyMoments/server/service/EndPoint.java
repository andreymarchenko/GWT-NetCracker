package ru.happyMoments.server.service;

import com.google.gwt.user.client.Event;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/events")
public class EndPoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LightEventDto> loadAllEvents() {
        List<LightEventDto> lightEvents = new ArrayList<LightEventDto>();
        lightEvents.add(new LightEventDto(56.32867, 44.00205));
        lightEvents.add(new LightEventDto(56.322, 44.098));
        lightEvents.add(new LightEventDto(56.331, 44.008));
        return lightEvents;
    }

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public EventDto getEventByLatLng(EventDto eventDto) {

        ArrayList<EventDto> eventDtos = new ArrayList<>();

        eventDtos.clear();
        eventDtos.add(new EventDto(1,
                "This is first event",
                new Date(),
                "First event",
                new ImageDto(1, "http://www.google.com/images/logo.gif"),
                "21:00",
                56.32867,
                44.00205));

        eventDtos.add(new EventDto(2,
                "This is second event",
                new Date(),
                "Second event",
                new ImageDto(1, "http://www.google.com/images/logo.gif"),
                "23:00",
                56.322,
                44.098));

        eventDtos.add(new EventDto(3,
                "This is third event",
                new Date(),
                "Third event",
                new ImageDto(1, "http://www.google.com/images/logo.gif"),
                "23:00",
                56.322,
                44.098));

        EventDto e = new EventDto();

        for (int i = 0; i < eventDtos.size(); i++) {
            if (eventDtos.get(i).getLatitude() == eventDto.getLatitude() &&
                    eventDtos.get(i).getLongitude() == eventDto.getLongitude())
                e = eventDtos.get(i);
        }

        return e;
    }*/

}