package ru.happyMoments.server.service;

import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.EventDtoFactory;
import ru.happyMoments.shared.factories.ImageDtoFactory;
import ru.happyMoments.shared.factories.LightEventDtoFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/events")
public class EndPoint {

    ClassLoader classLoader = getClass().getClassLoader();
    private String path = classLoader.getResource("Events.db").getFile();
    private Connection connection;

    private void createConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LightEventDto> loadAllEvents() {
        List<LightEventDto> lightEvents = new ArrayList<LightEventDto>();

        PreparedStatement data = null;
        ResultSet receivedData = null;

        try {
            createConnection();
            data = connection.prepareStatement("SELECT * FROM LIGHTEVENTS; ");
            receivedData = data.executeQuery();
            while (receivedData.next()) {
                lightEvents.add(LightEventDtoFactory.create(receivedData.getDouble(1), receivedData.getDouble(2)));
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

        EventDto eventDto = null;
        PreparedStatement data = null;
        ResultSet receivedData = null;

        try {
            createConnection();
            data = connection.prepareStatement("SELECT id,description,date,name,time,latitude,longitude FROM EVENTS WHERE (latitude BETWEEN ? AND ?) AND (longitude BETWEEN ? AND ?); ");

            data.setDouble(1, lightEventDto.getLatitude() - 0.0001);
            data.setDouble(2, lightEventDto.getLatitude() + 0.0001);

            data.setDouble(3, lightEventDto.getLongitude() - 0.0001);
            data.setDouble(4, lightEventDto.getLongitude() + 0.0001);

            receivedData = data.executeQuery();

            while (receivedData.next()) {
                PreparedStatement image = null;
                ResultSet receivedImage = null;

                image = connection.prepareStatement("SELECT event_id,url FROM IMAGES WHERE event_id=?; ");
                image.setInt(1, receivedData.getInt(1));
                receivedImage = image.executeQuery();

                int imageId = 0;
                String url = "";

                while (receivedImage.next()) {
                    imageId = receivedImage.getInt(1);
                    url = receivedImage.getString(2);
                }

                eventDto = EventDtoFactory.create(
                        receivedData.getInt(1),
                        receivedData.getString(2),
                        receivedData.getString(3),
                        receivedData.getString(4),
                        ImageDtoFactory.create(imageId, url),
                        receivedData.getString(5),
                        receivedData.getDouble(6),
                        receivedData.getDouble(7));

            }
            data.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventDto;
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<LightEventDto> createEvent(EventDto eventDto) {
        List<LightEventDto> lightEvents = new ArrayList<LightEventDto>();

        PreparedStatement eventsData = null;
        PreparedStatement lightEventsData = null;
        PreparedStatement imagesData = null;
        PreparedStatement data = null;

        ResultSet receivedLightEventsData = null;

        try {
            createConnection();

            eventsData = connection.prepareStatement("INSERT INTO EVENTS (id, description, date, name, time, latitude, longitude)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?); ");

            eventsData.setInt(1, eventDto.getId());
            eventsData.setString(2, eventDto.getDescription());
            eventsData.setString(3, eventDto.getDate());
            eventsData.setString(4, eventDto.getName());
            eventsData.setString(5, eventDto.getTime());
            eventsData.setDouble(6, eventDto.getLatitude());
            eventsData.setDouble(7, eventDto.getLongitude());

            eventsData.executeUpdate();
            eventsData.close();

            lightEventsData = connection.prepareStatement("INSERT INTO LIGHTEVENTS (latitude, longitude)" +
                    " VALUES (?, ?); ");

            lightEventsData.setDouble(1, eventDto.getLatitude());
            lightEventsData.setDouble(2, eventDto.getLongitude());

            lightEventsData.executeUpdate();
            lightEventsData.close();

            imagesData = connection.prepareStatement("INSERT INTO IMAGES (event_id, url)" +
                    " VALUES (?, ?); ");

            imagesData.setInt(1, eventDto.getId());
            imagesData.setString(2, eventDto.getImage().getUrl());

            imagesData.executeUpdate();
            imagesData.close();

            data = connection.prepareStatement("SELECT * FROM LIGHTEVENTS; ");
            receivedLightEventsData = data.executeQuery();
            while (receivedLightEventsData.next()) {
                lightEvents.add(LightEventDtoFactory.create(receivedLightEventsData.getDouble(1), receivedLightEventsData.getDouble(2)));
            }

            data.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lightEvents;
    }
}