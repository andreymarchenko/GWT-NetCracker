package ru.scrumdev.sample.client.events;

import com.google.gwt.event.shared.GwtEvent;

class ChangeMemoriaEvent extends GwtEvent<ChangeMemoriaEventHandler> {

    public static Type<ChangeMemoriaEventHandler> TYPE = new Type<ChangeMemoriaEventHandler>();

    @Override
    public Type<ChangeMemoriaEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeMemoriaEventHandler changeMemoriaEventHandler) {
        changeMemoriaEventHandler.onChangeMemoria(this);
    }
}
