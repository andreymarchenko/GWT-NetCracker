package ru.scrumdev.sample.client.controller;

import ru.scrumdev.sample.client.model.DataModel;
import ru.scrumdev.sample.client.model.Event;

import javax.inject.Inject;

public class Controller {

    private final DataModel dataModel;

    @Inject
    public Controller(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setDataModel(Event event) {
        this.dataModel.setEvent(event);
    }
}
