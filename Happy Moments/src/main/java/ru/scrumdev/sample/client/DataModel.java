package ru.scrumdev.sample.client;

public class DataModel {

    private Presenter presenter;
    private Event event;

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
