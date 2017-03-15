package ru.scrumdev.sample.client.controller;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.scrumdev.sample.client.commands.LoadDataCommand;
import ru.scrumdev.sample.client.commands.LoadDataCommandHandler;
import ru.scrumdev.sample.client.events.ChangeDataEvent;
import ru.scrumdev.sample.client.events.ChangeDataEventHandler;
import ru.scrumdev.sample.client.model.DataModel;
import ru.scrumdev.sample.client.model.Event;
import ru.scrumdev.sample.client.presenter.Presenter;

import javax.inject.Inject;

public class Controller {

    private final DataModel dataModel;
    private EventBus eventBus;
    private Presenter presenter;

    @Inject
    public Controller(final DataModel dataModel,
                      EventBus eventBus) {
        this.dataModel = dataModel;
        this.eventBus = eventBus;
        bind();
    }

    public void bind() {
        eventBus.addHandler(LoadDataCommand.TYPE, new LoadDataCommandHandler() {
            @Override
            public void onLoadData(LoadDataCommand loadDataCommand) {
                dataModel.setEvent(new Event());
            }
        });
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setDataToModel(Event event) {
        this.dataModel.setEvent(event);
    }
}
