package ru.scrumdev.sample.client;


public class Controller {

    private DataModel dataModel;

    public Controller(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setDataModel(Event event) {
        this.dataModel.setEvent(event);
    }
}
