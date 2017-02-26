package ru.scrumdev.sample.client;

import java.util.Date;
import java.util.List;

public class DataModel {

    private Event event;

    public DataModel() {
        event = new Event();
    }

    public DataModel(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
