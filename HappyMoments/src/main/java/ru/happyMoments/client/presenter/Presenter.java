package ru.happyMoments.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.happyMoments.client.commands.LoadDataCommand;
import ru.happyMoments.client.commands.LoadDataCommandHandler;
import ru.happyMoments.client.controller.Controller;
import ru.happyMoments.client.entity.Event;
import ru.happyMoments.client.events.ChangeDataEvent;
import ru.happyMoments.client.events.ChangeDataEventHandler;
import ru.happyMoments.client.model.DataModel;
import ru.happyMoments.client.service.EndPoint;
import ru.happyMoments.client.view.View;
import ru.happyMoments.shared.dto.EventDto;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Presenter {
    private Controller controller;
    private DataModel dataModel;
    private View view;
    private EventBus eventBus;

    private static final Logger logger = Logger.getLogger(Presenter.class.getName());

    private final EndPoint endPoint = GWT.create(EndPoint.class);

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
    }

    public void bind() {
        dataModel.addDataChangedEventHandler(new ChangeDataEventHandler() {
            @Override
            public void onChangeData(ChangeDataEvent changeDataEvent) {
                view.updateFields(dataModel.getEvent());
            }
        });
        eventBus.addHandler(LoadDataCommand.TYPE, new LoadDataCommandHandler() {
            @Override
            public void onLoadData(Event event, LoadDataCommand loadDataCommand) {
                endPoint.loadEvent(new MethodCallback<EventDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        logger.log(Level.SEVERE, "Error", throwable);
                    }

                    @Override
                    public void onSuccess(Method method, EventDto eventDto) {
                        view.getLabel().setText(eventDto.toString());
                    }
                });
            }
        });
    }

    public void loadData(Event event) {
        eventBus.fireEvent(LoadDataCommand.create(event));
    }

}
