package ru.happyMoments.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.Image;
import com.google.web.bindery.event.shared.EventBus;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import ru.happyMoments.client.commands.*;
import ru.happyMoments.client.model.EventDataModel;
import ru.happyMoments.client.model.EventListDataModel;
import ru.happyMoments.client.service.EndPoint;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.inject.Inject;
import java.util.List;

public class Controller {

    private final EventDataModel eventDataModel;
    private final EventListDataModel eventListDataModel;

    private EventBus eventBus;

    private final EndPoint endPoint = GWT.create(EndPoint.class);

    @Inject
    public Controller(final EventDataModel eventDataModel,
                      final EventListDataModel eventListDataModel,
                      EventBus eventBus) {
        this.eventDataModel = eventDataModel;
        this.eventBus = eventBus;
        this.eventListDataModel = eventListDataModel;
        bind();
    }

    public void bind() {
        eventBus.addHandler(LoadDataCommand.TYPE, new LoadDataCommandHandler() {
            @Override
            public void onLoadData(LightEventDto lightEventDto, LoadDataCommand loadDataCommand) {
                endPoint.getEventByLatLng(lightEventDto, new MethodCallback<EventDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, EventDto eventDto) {
                        eventDataModel.setEvent(eventDto);
                    }
                });
            }
        });

        eventBus.addHandler(LoadLightEventsCommand.TYPE, new LoadLightEventsCommandHandler() {
            @Override
            public void onLaunchApp(LoadLightEventsCommand loadLightEventsCommand) {
                endPoint.loadAllLightEvents(new MethodCallback<List<LightEventDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                    }

                    @Override
                    public void onSuccess(Method method, List<LightEventDto> lightEventDtos) {
                        eventListDataModel.setLightEvents(lightEventDtos);
                    }
                });
            }
        });

        eventBus.addHandler(CreateEventCommand.TYPE, new CreateEventCommandHandler() {
            @Override
            public void onCreateEvent(EventDto eventDto, CreateEventCommand createEventCommand) {
                endPoint.createEvent(eventDto, new MethodCallback() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, Object o) {
                        eventBus.fireEvent(LoadLightEventsCommand.create());
                    }
                });
            }
        });

        eventBus.addHandler(EditEventCommand.TYPE, new EditEventCommandHandler() {
            @Override
            public void onEditEvent(EventDto eventDto, EditEventCommand editEventCommand) {
                endPoint.editEvent(eventDto, new MethodCallback() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, Object o) {
                        eventBus.fireEvent(LoadLightEventsCommand.create());
                    }
                });
            }
        });

        eventBus.addHandler(DeleteEventCommand.TYPE, new DeleteEventCommandHandler() {
            @Override
            public void onDeleteEvent(EventDto eventDto, DeleteEventCommand deleteEventCommand) {
                endPoint.deleteEvent(eventDto, new MethodCallback() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, Object o) {
                        eventBus.fireEvent(LoadLightEventsCommand.create());
                    }
                });
            }
        });

        eventBus.addHandler(UploadImageCommand.TYPE, new UploadImageCommandHandler() {
            @Override
            public void onUpdateImage(final File file, UploadImageCommand uploadImageCommand) {

                final FileReader reader = new FileReader();
                reader.addLoadHandler(new LoadHandler() {
                    @Override
                    public void onLoad(LoadEvent event) {
                        endPoint.uploadImage(reader.getStringResult(), new MethodCallback() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {

                            }

                            @Override
                            public void onSuccess(Method method, Object o) {
                            }
                        });
                    }
                });

                reader.readAsDataURL(file);

            }
        });

    }
}
