package ru.scrumdev.sample.client;

public class Controller {
    private DataModel dataModel;

    public Controller() {
    }

    public Controller(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
}
