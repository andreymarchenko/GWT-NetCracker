package ru.happyMoments.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.happyMoments.client.commands.*;
import ru.happyMoments.client.model.EventDataModel;
import ru.happyMoments.client.model.EventListDataModel;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.client.service.EndPoint;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

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
                endPoint.createEvent(eventDto, new MethodCallback<List<LightEventDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, List<LightEventDto> lightEventDtos) {
                        //команду loadLightEvent кидать здесь
                        eventListDataModel.setLightEvents(lightEventDtos);
                    }
                });
            }
        });

    }
}
