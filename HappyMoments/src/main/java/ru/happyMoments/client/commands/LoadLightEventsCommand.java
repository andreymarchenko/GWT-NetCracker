package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.GwtEvent;

public class LoadLightEventsCommand extends GwtEvent<LoadLightEventsCommandHandler> {

    public static Type<LoadLightEventsCommandHandler> TYPE = new Type<>();

    @Override
    public Type<LoadLightEventsCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadLightEventsCommandHandler loadLightEventsCommandHandler) {
        loadLightEventsCommandHandler.onLaunchApp(this);
    }

    public static LoadLightEventsCommand create() {
        return new LoadLightEventsCommand();
    }

}
