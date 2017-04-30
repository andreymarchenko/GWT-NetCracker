package ru.happyMoments.client.service;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Image;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.vectomatic.file.File;
import ru.happyMoments.shared.constants.Paths;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    void uploadImage(String string,
                     MethodCallback callback);

}
