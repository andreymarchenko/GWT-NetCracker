package ru.happyMoments.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class LoadLightDataEvent extends GwtEvent<LoadLightDataEventHandler>{

    public static Type<LoadLightDataEventHandler> TYPE = new Type<LoadLightDataEventHandler>();

    @Override
    public Type<LoadLightDataEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadLightDataEventHandler handler) {
        handler.onLoadLightData(this);
    }

    public static LoadLightDataEvent create() {
        return new LoadLightDataEvent();
    }
}
