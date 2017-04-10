package ru.happyMoments.client.model;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import ru.happyMoments.client.events.LoadLightDataEvent;
import ru.happyMoments.client.events.LoadLightDataEventHandler;
import ru.happyMoments.shared.dto.LightEventDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EventListDataModel {

    private List<LightEventDto> lightEventDtos;
    private EventBus eventBus;

    public EventListDataModel() {
    }

    @Inject
    public EventListDataModel(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public HandlerRegistration addLoadLightDataEventHandler(final LoadLightDataEventHandler handler) {
        return eventBus.addHandler(LoadLightDataEvent.TYPE, handler);
    }

    public List<LightEventDto> getLightEventDtos() {
        return lightEventDtos;
    }

    public void setLightEvents(ArrayList<LightEventDto> lightEventDtos) {

        if (this.lightEventDtos != null && lightEventDtos != null) {
            int index = 0;
            for (int i = 0; i < lightEventDtos.size(); i++) {
                if (this.lightEventDtos.get(i).equals(lightEventDtos.get(i))) {
                    index++;
                }
            }
            if (index == lightEventDtos.size()) return;
        } else if (lightEventDtos != null) {
            this.lightEventDtos = lightEventDtos;
            eventBus.fireEvent(LoadLightDataEvent.create());
        } else {

        }
    }

    public void setEventBus(EventBus eventBus) {
        if (eventBus != null) {
            this.eventBus = eventBus;
        }
    }
}
