package ru.scrumdev.sample.client.presenter;

import ru.scrumdev.sample.client.controller.Controller;
import ru.scrumdev.sample.client.model.DataModel;
import ru.scrumdev.sample.client.model.Event;
import ru.scrumdev.sample.client.view.View;

import javax.inject.Inject;

public class Presenter {
    private Controller controller;

    @Inject
    public Presenter(Controller controller) {
        this.controller = controller;
    }

    public void loadData(Event event) {
        controller.setDataModel(event);
    }

}
