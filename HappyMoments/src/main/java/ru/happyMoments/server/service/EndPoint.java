package ru.happyMoments.server.service;

import com.google.gwt.typedarrays.shared.ArrayBuffer;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import ru.happyMoments.server.dao.JdbcDao;
import ru.happyMoments.server.dao.JdbcDaoImpl;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.staff.Creator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.List;

@Path("/events")
public class EndPoint {

    private JdbcDao jdbcDao = new JdbcDaoImpl();

    @Path("/loadallevents")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LightEventDto> loadAllEvents() {
        return jdbcDao.loadAllEvents();
    }

    @Path("/loadevent")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventDto getEventByLatLng(LightEventDto lightEventDto) {
        return jdbcDao.getEventByLatLng(lightEventDto);
    }

    @Path("/create")
    @POST
    public void createEvent(EventDto eventDto) {
        jdbcDao.createEvent(eventDto);
    }

    @Path("/edit")
    @POST
    public void editEvent(EventDto eventDto) {
        jdbcDao.editEvent(eventDto);
    }

    @Path("/delete")
    @POST
    public void deleteEvent(EventDto eventDto) {
        jdbcDao.deleteEvent(eventDto);
    }

    @Path("/upload")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void uploadImage(String image) {
        jdbcDao.upload(image);
    }
}