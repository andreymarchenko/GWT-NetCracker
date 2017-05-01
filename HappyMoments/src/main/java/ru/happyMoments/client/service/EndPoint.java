package ru.happyMoments.client.service;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.happyMoments.shared.constants.Paths;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api")
public interface EndPoint extends RestService {

    @GET
    @Path(Paths.LOAD_ALL_EVENTS_PATH)
    void loadAllLightEvents(MethodCallback<List<LightEventDto>> callback);

    @POST
    @Path(Paths.LOAD_EVENT_PATH)
    void getEventByLatLng(LightEventDto lightEventDto,
                          MethodCallback<EventDto> callback);

    @POST
    @Path(Paths.CREATE_PATH)
    void createEvent(EventDto EventDto,
                     MethodCallback callback);

    @POST
    @Path(Paths.EDIT_PATH)
    void editEvent(EventDto EventDto,
                   MethodCallback callback);

    @POST
    @Path(Paths.DELETE_PATH)
    void deleteEvent(EventDto EventDto,
                     MethodCallback callback);

    @POST
    @Path(Paths.UPLOAD_PATH)
    void uploadImage(String image,
                     MethodCallback callback);

}
