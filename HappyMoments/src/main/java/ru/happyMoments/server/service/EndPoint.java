package ru.happyMoments.server.service;

import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;
import ru.happyMoments.shared.dto.LightEventDto;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LightEventDto> loadAllEvents() {
        List<LightEventDto> lightEvents = new ArrayList<LightEventDto>();

        Connection connection = null;
        PreparedStatement data = null;
        ResultSet receivedData = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            data = connection.prepareStatement("SELECT * FROM LIGHTEVENTS; ");
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

        EventDto eventDto = null;
        Connection connection = null;
        PreparedStatement data = null;
        ResultSet receivedData = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
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

                while(receivedImage.next()) {
                    imageId = receivedImage.getInt(1);
                    url = receivedImage.getString(2);
                }

                eventDto = new EventDto(
                        receivedData.getInt(1),
                        receivedData.getString(2),
                        receivedData.getString(3),
                        receivedData.getString(4),
                        new ImageDto(imageId, url),
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
}