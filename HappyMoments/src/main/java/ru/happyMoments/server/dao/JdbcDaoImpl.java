package ru.happyMoments.server.dao;

import ru.happyMoments.shared.constants.Queries;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.Factory;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDaoImpl implements JdbcDao {

    private final String PATH = "jdbc:sqlite:" + getClass().getClassLoader().getResource("Events.db").getFile();

    private Connection connection;

    private Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(PATH);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public List<LightEventDto> loadAllEvents() {
        List<LightEventDto> lightEvents = new ArrayList<LightEventDto>();

        PreparedStatement data;
        ResultSet receivedData;

        try {
            data = getConnection().prepareStatement(Queries.LOAD_ALL_EVENTS);
            receivedData = data.executeQuery();
            while (receivedData.next()) {
                lightEvents.add(Factory.createLightEventDto(receivedData.getDouble(1), receivedData.getDouble(2)));
            }
            receivedData.close();
            data.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lightEvents;
    }

    @Override
    public EventDto getEventByLatLng(LightEventDto lightEventDto) {
        EventDto eventDto = null;
        PreparedStatement data;
        ResultSet receivedData;

        try {
            data = getConnection().prepareStatement(Queries.GET_EVENT_BY_LATITUDE_LONGITUDE);

            data.setDouble(1, lightEventDto.getLatitude() - 0.0001);
            data.setDouble(2, lightEventDto.getLatitude() + 0.0001);

            data.setDouble(3, lightEventDto.getLongitude() - 0.0001);
            data.setDouble(4, lightEventDto.getLongitude() + 0.0001);

            receivedData = data.executeQuery();

            while (receivedData.next()) {
                PreparedStatement image = null;
                ResultSet receivedImage = null;

                image = connection.prepareStatement(Queries.GET_IMAGE);
                image.setInt(1, receivedData.getInt(1));
                receivedImage = image.executeQuery();

                int imageId = 0;
                String url = "";

                imageId = receivedImage.getInt(1);
                url = receivedImage.getString(2);

                receivedImage.close();

                ImageDto mg = Factory.createImageDto(imageId, url);

                eventDto = Factory.createEventDto(
                        receivedData.getInt(1),
                        receivedData.getString(2),
                        receivedData.getString(3),
                        receivedData.getString(4),
                        Factory.createImageDto(imageId, url),
                        receivedData.getString(5),
                        receivedData.getDouble(6),
                        receivedData.getDouble(7));

                image.close();
            }

            data.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventDto;
    }

    @Override
    public void createEvent(EventDto eventDto) {
        PreparedStatement eventsData;

        try {
            eventsData = getConnection().prepareStatement(Queries.CREATE_EVENT);

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

    @Override
    public void editEvent(EventDto eventDto) {

        PreparedStatement eventsData;

        try {
            eventsData = getConnection().prepareStatement(Queries.EDIT_EVENT);

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

    @Override
    public void deleteEvent(EventDto eventDto) {
        PreparedStatement eventsData;
        PreparedStatement imagesData;

        try {
            eventsData = getConnection().prepareStatement(Queries.DELETE_EVENT);
            imagesData = getConnection().prepareStatement(Queries.DELETE_IMAGE);

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

    @Override
    public void upload(File file) {
        PreparedStatement fileData;
        PreparedStatement numEventsData;
        ResultSet receivedData;
        int number = 0;
        try {
            fileData = getConnection().prepareStatement(Queries.UPLOAD_IMAGE);
            numEventsData = getConnection().prepareStatement(Queries.GET_EVENTS_NUMBER);

            receivedData = numEventsData.executeQuery();

            number = receivedData.getInt(1);

            receivedData.close();

            fileData.setInt(1, number + 1);
            fileData.setString(2, "http://127.0.0.1:8888/images/" + file.getName());

            fileData.executeUpdate();

            numEventsData.close();
            fileData.close();

        } catch (SQLException e) {

        }
    }
}
