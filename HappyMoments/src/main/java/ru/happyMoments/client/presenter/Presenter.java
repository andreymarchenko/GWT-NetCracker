package ru.happyMoments.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.happyMoments.client.commands.LoadDataCommand;
import ru.happyMoments.client.commands.LoadDataCommandHandler;
import ru.happyMoments.client.controller.Controller;
import ru.happyMoments.client.events.ChangeDataEvent;
import ru.happyMoments.client.events.ChangeDataEventHandler;
import ru.happyMoments.client.model.DataModel;
import ru.happyMoments.client.view.View;

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
    }

    public HandlerRegistration addLoadDataHandler(final LoadDataCommandHandler handler) {
        return eventBus.addHandler(LoadDataCommand.TYPE, handler);
    }

    public void loadData() {
        eventBus.fireEvent(LoadDataCommand.create());
    }

}
