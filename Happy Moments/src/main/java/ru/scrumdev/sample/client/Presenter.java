package ru.scrumdev.sample.client;

import ru.scrumdev.sample.client.ui.View;

import javax.inject.Inject;

public class Presenter {
    private Controller controller;
    private DataModel dataModel;
    private View view;

    @Inject
    public Presenter(View view) {
        this.view = view;
        dataModel = new DataModel();
        dataModel.setPresenter(this);
        controller = new Controller(dataModel);
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
