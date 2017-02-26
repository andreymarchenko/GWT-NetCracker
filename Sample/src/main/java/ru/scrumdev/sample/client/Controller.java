package ru.scrumdev.sample.client;

public class Controller {
    private DataModel dataModel;

    public Controller() {}

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setDataModel() {
        dataModel = new DataModel();
    }
}
