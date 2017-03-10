package ru.scrumdev.sample.client.presenter;

import ru.scrumdev.sample.client.controller.Controller;
import ru.scrumdev.sample.client.model.DataModel;
import ru.scrumdev.sample.client.model.Event;
import ru.scrumdev.sample.client.view.View;

import javax.inject.Inject;

public class Presenter {
    private Controller controller;
    private DataModel dataModel;
    private View view;

    @Inject
    public Presenter(View view, Controller controller, DataModel dataModel) {
        this.view = view;
        this.controller = controller;
        this.dataModel = dataModel;
    }

    public void setPresenter() {
        dataModel.setPresenter(this);
    }

    public void loadData(Event event) {
        controller.setDataModel(event);
    }

    public void notify(Event event) {
        view.getName().setText(event.getName());
        view.getDescription().setText(event.getDescription());
        view.getDate().setText(event.getDate().toString());
        view.getTime().setText(event.getTime());
    }

}
