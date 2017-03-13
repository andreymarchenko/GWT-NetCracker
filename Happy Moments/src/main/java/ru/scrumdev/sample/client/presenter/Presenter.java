package ru.scrumdev.sample.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.scrumdev.sample.client.commands.LoadDataCommand;
import ru.scrumdev.sample.client.commands.LoadDataCommandHandler;
import ru.scrumdev.sample.client.controller.Controller;
import ru.scrumdev.sample.client.events.ChangeDataEvent;
import ru.scrumdev.sample.client.events.ChangeDataEventHandler;
import ru.scrumdev.sample.client.model.DataModel;
import ru.scrumdev.sample.client.model.Event;
import ru.scrumdev.sample.client.view.View;

import javax.inject.Inject;

public class Presenter {
    private Controller controller;
    private DataModel dataModel;
    private View view;
    private EventBus eventBus;

    @Inject
    public Presenter(Controller controller,
                     final DataModel dataModel,
                     View view,
                     EventBus eventBus) {
        this.controller = controller;
        this.dataModel = dataModel;
        this.view = view;
        this.eventBus = eventBus;
        view.setPresenter(this);
        controller.setPresenter(this);
    }

    public void bind() {
        dataModel.addDataChangedEventHandler(new ChangeDataEventHandler() {
            @Override
            public void onChangeData(ChangeDataEvent changeDataEvent) {
                view.updateFields(dataModel.getEvent());
            }
        });
        controller.bind();
    }

    public HandlerRegistration addLoadDataHandler(final LoadDataCommandHandler handler) {
        return eventBus.addHandler(LoadDataCommand.TYPE, handler);
    }

    public void loadData() {
        //controller.setDataToModel(new Event());
        eventBus.fireEvent(LoadDataCommand.create());
    } //Create command

}
