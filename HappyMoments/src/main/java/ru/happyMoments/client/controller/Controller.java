package ru.happyMoments.client.controller;

import com.google.gwt.event.shared.EventBus;
import ru.happyMoments.client.commands.LoadDataCommand;
import ru.happyMoments.client.commands.LoadDataCommandHandler;
import ru.happyMoments.client.entity.Event;
import ru.happyMoments.client.model.DataModel;

import javax.inject.Inject;

public class Controller {

    private final DataModel dataModel;
    private EventBus eventBus;

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
            public void onLoadData(Event event, LoadDataCommand loadDataCommand) {
                dataModel.setEvent(event);
            }
        });
    }
}
