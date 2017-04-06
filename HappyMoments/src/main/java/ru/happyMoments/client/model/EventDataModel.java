package ru.happyMoments.client.model;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.happyMoments.client.events.ChangeDataEvent;
import ru.happyMoments.client.events.ChangeDataEventHandler;
import ru.happyMoments.shared.dto.EventDto;

import javax.inject.Inject;

public class EventDataModel {

    private EventDto event;
    private EventBus eventBus;

    public EventDataModel() {
    }

    @Inject
    public void EventDataModel(EventDto event, EventBus eventBus) {
        this.event = event;
        this.eventBus = eventBus;
    }

    public HandlerRegistration addDataChangedEventHandler(final ChangeDataEventHandler handler) {
        return eventBus.addHandler(ChangeDataEvent.TYPE, handler);
    }

    public void setEvent(EventDto event) {
        if (event != null && this.event.equals(event)) return;
        else {
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
