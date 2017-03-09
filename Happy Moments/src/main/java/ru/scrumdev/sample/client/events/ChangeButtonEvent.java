package ru.scrumdev.sample.client.events;

import com.google.gwt.event.shared.GwtEvent;
import ru.scrumdev.sample.client.eventHandlers.ChangeButtonStateHandler;

public class ChangeButtonEvent extends GwtEvent<ChangeButtonStateHandler>{

    public static Type<ChangeButtonStateHandler> TYPE = new Type<ChangeButtonStateHandler>();

    @Override
    public Type<ChangeButtonStateHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeButtonStateHandler changeButtonStateHandler) {
        changeButtonStateHandler.onChangeButtonState(this);
    }
}
