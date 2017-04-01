package ru.happyMoments.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.happyMoments.client.commands.LaunchAppCommand;
import ru.happyMoments.client.commands.LaunchAppCommandHandler;
import ru.happyMoments.client.commands.LoadDataCommand;
import ru.happyMoments.client.commands.LoadDataCommandHandler;
import ru.happyMoments.client.model.EventDataModel;
import ru.happyMoments.client.model.EventListDataModel;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.client.service.EndPoint;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Controller {

    private final EventDataModel eventDataModel;
    private final EventListDataModel eventListDataModel;

    private EventBus eventBus;

    private static final Logger logger = Logger.getLogger(Presenter.class.getName());
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

        eventBus.addHandler(LaunchAppCommand.TYPE, new LaunchAppCommandHandler() {
            @Override
            public void onLaunchApp(LaunchAppCommand launchAppCommand) {
                endPoint.loadAllLightEvents(new MethodCallback<List<LightEventDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                    }

                    @Override
                    public void onSuccess(Method method, List<LightEventDto> lightEventDtos) {
                        eventListDataModel.setLightEvents((ArrayList<LightEventDto>) lightEventDtos);
                    }
                });
            }
        });


    }
}
