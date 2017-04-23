package ru.happyMoments.client.service;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.ws.rs.*;
import java.util.List;

@Path("/api")
public interface EndPoint extends RestService {

    static final String PATH = "/events";
    static final String CREATE_PATH = "/events/create";

    @GET
    @Path(PATH)
    void loadAllLightEvents(MethodCallback<List<LightEventDto>> callback);

    @POST
    @Path(PATH)
    void getEventByLatLng(LightEventDto lightEventDto,
                          MethodCallback<EventDto> callback);

    @POST
    @Path(CREATE_PATH)
    void createEvent(EventDto EventDto,
                     MethodCallback<List<LightEventDto>> callback);
}
