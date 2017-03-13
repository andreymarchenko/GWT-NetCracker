package ru.scrumdev.sample.client.model;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.scrumdev.sample.client.events.ChangeDataEvent;
import ru.scrumdev.sample.client.events.ChangeDataEventHandler;

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
        this.event = event;
        eventBus.fireEvent(ChangeDataEvent.create());
    }

    public Event getEvent() {
        return event;
    }
}
