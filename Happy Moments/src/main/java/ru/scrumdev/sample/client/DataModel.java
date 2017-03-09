package ru.scrumdev.sample.client;

import javax.inject.Inject;

public class DataModel {

    private Presenter presenter;
    private Event event;

    public Event getEvent() {
        return event;
    }

    @Inject
    public void DataModel(Presenter presenter, Event event) {
        this.presenter = presenter;
        this.event = event;
    }

    public void setEvent(Event event) {
        this.event = event;
        presenter.notify(event);
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
