package ru.happyMoments.client.service;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.ws.rs.*;
import java.util.List;

@Path("/api")
public interface EndPoint extends RestService {

    @GET
    @Path("/events")
    void loadAllLightEvents(MethodCallback<List<LightEventDto>> callback);

    @POST
    @Path("/events")
    void getEventByLatLng(LightEventDto lightEventDto, MethodCallback<EventDto> callback);

}
