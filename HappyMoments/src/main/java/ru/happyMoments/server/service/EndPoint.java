package ru.happyMoments.server.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/events")
public interface EndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void loadEvent();
}
