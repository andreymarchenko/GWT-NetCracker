package ru.scrumdev.sample.client;

public class Controller {
    private DataModel dataModel;

    public Controller() {
        this.dataModel = new DataModel();
    }

    public Controller(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
}
