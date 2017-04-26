package ru.happyMoments.server.service;

import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.Factory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/events")
public class EndPoint {

    ClassLoader classLoader = getClass().getClassLoader();
    private final String PATH = "jdbc:sqlite:" + classLoader.getResource("Events.db").getFile();

    private Connection connection;

    private Connection getConnection() {
        String s= classLoader.getResource("Events.db").getFile();
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(PATH);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LightEventDto> loadAllEvents() {
        List<LightEventDto> lightEvents = new ArrayList<LightEventDto>();

        PreparedStatement data;
        ResultSet receivedData;

        try {
            data = getConnection().prepareStatement("SELECT latitude,longitude FROM EVENTS; ");
            receivedData = data.executeQuery();
            while (receivedData.next()) {
                lightEvents.add(Factory.createLightEventDto(receivedData.getDouble(1), receivedData.getDouble(2)));
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
        PreparedStatement data;
        ResultSet receivedData;

        try {
            data = getConnection().prepareStatement("SELECT id,description,date,name,time,latitude,longitude FROM EVENTS WHERE (latitude BETWEEN ? AND ?) AND (longitude BETWEEN ? AND ?); ");

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

                eventDto = Factory.createEventDto(
                        receivedData.getInt(1),
                        receivedData.getString(2),
                        receivedData.getString(3),
                        receivedData.getString(4),
                        Factory.createImageDto(imageId, url),
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

    private static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    @Path("/create")
    @POST
    public void createEvent(EventDto eventDto) {

        File newImage = new File(eventDto.getImage().getUrl(), "sample");
        File imageDirectory = new File("war\\images");

        try {
            copyFile(newImage, imageDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PreparedStatement eventsData;

        try {
            eventsData = getConnection().prepareStatement("INSERT INTO EVENTS (id, description, date, name, time, latitude, longitude)" +
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Path("/edit")
    @POST
    public void editEvent(EventDto eventDto) {

        PreparedStatement eventsData;

        try {
            eventsData = getConnection().prepareStatement("UPDATE EVENTS SET description=?, date=?, name=?, time=?, latitude=?, longitude=? WHERE id=?; ");

            eventsData.setString(1, eventDto.getDescription());
            eventsData.setString(2, eventDto.getDate());
            eventsData.setString(3, eventDto.getName());
            eventsData.setString(4, eventDto.getTime());
            eventsData.setDouble(5, eventDto.getLatitude());
            eventsData.setDouble(6, eventDto.getLongitude());
            eventsData.setInt(7, eventDto.getId());

            eventsData.executeUpdate();
            eventsData.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Path("/delete")
    @POST
    public void deleteEvent(EventDto eventDto) {

        PreparedStatement eventsData;
        PreparedStatement imagesData;

        try {
            eventsData = getConnection().prepareStatement("DELETE FROM EVENTS WHERE id=?; ");
            imagesData = getConnection().prepareStatement("DELETE FROM IMAGES WHERE event_id=?; ");

            eventsData.setInt(1, eventDto.getId());
            imagesData.setInt(1, eventDto.getId());

            eventsData.executeUpdate();
            eventsData.close();

            imagesData.executeUpdate();
            imagesData.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}