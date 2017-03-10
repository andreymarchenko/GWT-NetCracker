package ru.scrumdev.sample.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.scrumdev.sample.client.eventHandlers.ChangeButtonEventHandler;

public class ChangeButtonEvent extends GwtEvent<ChangeButtonEventHandler>{

    public static Type<ChangeButtonEventHandler> TYPE = new Type<ChangeButtonEventHandler>();

    @Override
    public Type<ChangeButtonEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeButtonEventHandler changeButtonStateHandler) {
        changeButtonStateHandler.onChangeButtonState(this);
    }
}
