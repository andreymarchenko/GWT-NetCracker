package ru.happyMoments.client.service;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.happyMoments.shared.dto.EventDto;

import javax.ws.rs.*;

@Path("/api")
public interface EndPoint extends RestService {

    @GET
    @Path("/events")
    void loadEvent(MethodCallback<EventDto> callback);
}
