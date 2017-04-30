package ru.happyMoments.shared.constants;

public class Queries {

    public static final String LOAD_ALL_EVENTS = "SELECT latitude,longitude FROM EVENTS; ";

    public static final String GET_EVENT_BY_LATITUDE_LONGITUDE = "SELECT id,description,date,name,time,latitude,longitude FROM EVENTS WHERE (latitude BETWEEN ? AND ?) AND (longitude BETWEEN ? AND ?); ";

    public static final String GET_IMAGE = "SELECT event_id,url FROM IMAGES WHERE event_id=?; ";

    public static String CREATE_EVENT = "INSERT INTO EVENTS (id, description, date, name, time, latitude, longitude)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?); ";

    public static String EDIT_EVENT = "UPDATE EVENTS SET description=?, date=?, name=?, time=?, latitude=?, longitude=? WHERE id=?;";

    public static String DELETE_EVENT = "DELETE FROM EVENTS WHERE id=?; ";

    public static String DELETE_IMAGE = "DELETE FROM IMAGES WHERE event_id=?; ";
}
