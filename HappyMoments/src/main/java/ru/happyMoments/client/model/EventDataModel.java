package ru.happyMoments.client.model;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.happyMoments.client.events.ChangeDataEvent;
import ru.happyMoments.client.events.ChangeDataEventHandler;
import ru.happyMoments.shared.dto.EventDto;

import javax.inject.Inject;
import java.util.List;

public class EventDataModel {

    private EventDto event = null;
    private EventBus eventBus = null;

    public EventDataModel() {
    }

    @Inject
    public void DataModel(EventDto event,
                          EventBus eventBus) {
        this.event = event;
        this.eventBus = eventBus;
    }

    public HandlerRegistration addDataChangedEventHandler(final ChangeDataEventHandler handler) {
        return eventBus.addHandler(ChangeDataEvent.TYPE, handler);
    }

    public void setEvent(EventDto event) {
        if (event != null) {
            if (this.event.equals(event)) return;
            this.event = event;
            eventBus.fireEvent(ChangeDataEvent.create());
        }
    }

    public EventDto getEvent() {
        return event;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
