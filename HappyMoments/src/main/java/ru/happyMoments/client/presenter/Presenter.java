package ru.happyMoments.client.presenter;

import com.google.web.bindery.event.shared.EventBus;
import org.vectomatic.file.File;
import ru.happyMoments.client.commands.*;
import ru.happyMoments.client.controller.Controller;
import ru.happyMoments.client.events.ChangeDataEvent;
import ru.happyMoments.client.events.ChangeDataEventHandler;
import ru.happyMoments.client.events.LoadLightDataEvent;
import ru.happyMoments.client.events.LoadLightDataEventHandler;
import ru.happyMoments.client.model.EventDataModel;
import ru.happyMoments.client.model.EventListDataModel;
import ru.happyMoments.client.view.View;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.inject.Inject;


public class Presenter {
    private Controller controller;
    private EventDataModel eventDataModel;
    private EventListDataModel eventListDataModel;
    private View view;
    private EventBus eventBus;

    @Inject
    public Presenter(Controller controller,
                     final EventDataModel eventDataModel,
                     final EventListDataModel eventListDataModel,
                     View view,
                     EventBus eventBus) {
        this.controller = controller;
        this.eventDataModel = eventDataModel;
        this.eventListDataModel = eventListDataModel;
        this.view = view;
        this.eventBus = eventBus;
        view.setPresenter(this);
    }

    public void bind() {
        eventDataModel.addDataChangedEventHandler(new ChangeDataEventHandler() {
            @Override
            public void onChangeData(ChangeDataEvent changeDataEvent) {
                view.updateFields(eventDataModel.getEvent());
            }
        });

        eventListDataModel.addLoadLightDataEventHandler(new LoadLightDataEventHandler() {
            @Override
            public void onLoadLightData(LoadLightDataEvent loadLightDataEvent) {
                view.setLightData(eventListDataModel.getLightEventDtos());
            }
        });

    }

    public void loadEvent(LightEventDto lightEventDto) {
        eventBus.fireEvent(LoadDataCommand.create(lightEventDto));
    }

    public void createEvent(EventDto eventDto) {
        eventBus.fireEvent(CreateEventCommand.create(eventDto));
    }

    public void editEvent(EventDto eventDto) {
        eventBus.fireEvent(EditEventCommand.create(eventDto));
    }

    public void deleteEvent() {
        eventBus.fireEvent(DeleteEventCommand.create(getCurrentEventDto()));
    }

    public void uploadImage(File file) {eventBus.fireEvent(UploadImageCommand.create(file));}

    public EventDto getCurrentEventDto() {
        return eventDataModel.getEvent();
    }

}
