package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import ru.happyMoments.client.entity.Event;

public class LoadDataCommand extends GwtEvent<LoadDataCommandHandler> {

    private Event event;

    public LoadDataCommand(Event event) {
        this.event = event;
    }

    public static Type<LoadDataCommandHandler> TYPE = new Type<LoadDataCommandHandler>();

    @Override
    public Type<LoadDataCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadDataCommandHandler loadDataCommandHandler) {
        loadDataCommandHandler.onLoadData(event, this);
    }

    public static LoadDataCommand create(Event event) {
        return new LoadDataCommand(event);
    }
}
