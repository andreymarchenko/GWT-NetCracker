package ru.happyMoments.client.model;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.happyMoments.client.entity.Event;
import ru.happyMoments.client.events.ChangeDataEvent;
import ru.happyMoments.client.events.ChangeDataEventHandler;

import javax.inject.Inject;

public class DataModel {

    private Event event;
    private EventBus eventBus;

    @Inject
    public void DataModel(Event event,
                          EventBus eventBus) {
        this.event = event;
        this.eventBus = eventBus;
    }

    public HandlerRegistration addDataChangedEventHandler(final ChangeDataEventHandler handler) {
        return eventBus.addHandler(ChangeDataEvent.TYPE, handler);
    }

    public void setEvent(Event event) {
        if (event.equals(this.event)) return;
        else {
            this.event = event;
            eventBus.fireEvent(ChangeDataEvent.create());
        }
    }

    public Event getEvent() {
        return event;
    }
}
