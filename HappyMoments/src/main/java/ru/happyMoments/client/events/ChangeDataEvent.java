package ru.happyMoments.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ChangeDataEvent extends GwtEvent<ChangeDataEventHandler> {

    public static Type<ChangeDataEventHandler> TYPE = new Type<ChangeDataEventHandler>();

    @Override
    public Type<ChangeDataEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeDataEventHandler changeDataEventHandler) {
        changeDataEventHandler.onChangeData(this);
    }

    public static ChangeDataEvent create() {
        return new ChangeDataEvent();
    }
}
