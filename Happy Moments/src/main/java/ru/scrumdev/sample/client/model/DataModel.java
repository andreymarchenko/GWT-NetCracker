package ru.scrumdev.sample.client.model;

import javax.inject.Inject;

public class DataModel {

    private Event event;

    @Inject
    public void DataModel(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
