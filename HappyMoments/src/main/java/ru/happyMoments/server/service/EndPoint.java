package ru.happyMoments.server.service;

import com.google.gwt.user.client.Event;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/events")
public class EndPoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LightEventDto> loadAllEvents() {
        List<LightEventDto> lightEvents = new ArrayList<LightEventDto>();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\GWT-NetCracker\\HappyMoments\\Events.db");
            System.out.println("Opened database successfully");
        } catch (SQLException ex) {
        }

        PreparedStatement data = null;
        ResultSet receivedData = null;


        try {
            data = connection.prepareStatement("SELECT * FROM lightevents; ");
            receivedData = data.executeQuery();
            while (receivedData.next()) {
                lightEvents.add(new LightEventDto(receivedData.getDouble(1), receivedData.getDouble(2)));
            }
            data.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lightEvents;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventDto getEventByLatLng(LightEventDto lightEventDto) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Events.db");
            System.out.println("Opened database successfully");
        } catch (SQLException ex) {
        }

        PreparedStatement checkEvent;
        String str = "";

        ArrayList<EventDto> eventDtos = new ArrayList<>();

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
                56.331,
                44.008));

        EventDto e = new EventDto();

        for (int i = 0; i < eventDtos.size(); i++) {
            if (eventDtos.get(i).getLatitude() - lightEventDto.getLatitude() < 0.0001 && eventDtos.get(i).getLongitude() - lightEventDto.getLongitude() < 0.0001)
                e = eventDtos.get(i);
        }

        return e;
    }

}