package ru.happyMoments.client.model;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.happyMoments.client.events.LoadLightDataEvent;
import ru.happyMoments.client.events.LoadLightDataEventHandler;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EventListDataModel {

    private List<LightEventDto> lightEventDtos;
    private EventBus eventBus;

    public List<LightEventDto> getLightEventDtos() {
        return lightEventDtos;
    }

    @Inject
    public EventListDataModel(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public HandlerRegistration addLoadLightDataEventHandler(final LoadLightDataEventHandler handler) {
        return eventBus.addHandler(LoadLightDataEvent.TYPE, handler);
    }

    public void setLightEvents(ArrayList<LightEventDto> lightEventDtos) {
        this.lightEventDtos = lightEventDtos;
        if (lightEventDtos != null) {
            eventBus.fireEvent(LoadLightDataEvent.create());
        }
    }
}
