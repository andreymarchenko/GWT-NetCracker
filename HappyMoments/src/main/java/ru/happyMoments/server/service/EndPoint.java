package ru.happyMoments.server.service;

import org.vectomatic.file.File;
import ru.happyMoments.server.dao.JdbcDao;
import ru.happyMoments.server.dao.JdbcDaoImpl;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.List;

@Path("/events")
public class EndPoint{

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
    public void uploadImage(InputStream result) {

       // jdbcDao.upload(file);
    }
}