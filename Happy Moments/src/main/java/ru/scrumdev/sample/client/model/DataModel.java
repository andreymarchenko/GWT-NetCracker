package ru.scrumdev.sample.client.model;

import ru.scrumdev.sample.client.presenter.Presenter;

import javax.inject.Inject;

public class DataModel {

    private Presenter presenter;
    private Event event;

    @Inject
    public void DataModel(Presenter presenter, Event event) {
        this.presenter = presenter;
        this.event = event;
    }

    public Event getEvent() {
        return event;
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
