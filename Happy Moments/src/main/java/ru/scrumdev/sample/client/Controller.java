package ru.scrumdev.sample.client;

import javax.inject.Inject;

public class Controller {

    private DataModel dataModel;

    @Inject
    public Controller(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setDataModel(Event event) {
        this.dataModel.setEvent(event);
    }
}
