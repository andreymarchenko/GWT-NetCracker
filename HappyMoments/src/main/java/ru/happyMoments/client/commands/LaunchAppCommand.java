package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import ru.happyMoments.shared.dto.EventDto;

public class LaunchAppCommand extends GwtEvent<LaunchAppCommandHandler> {

    public static Type<LaunchAppCommandHandler> TYPE = new Type<>();

    @Override
    public Type<LaunchAppCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LaunchAppCommandHandler launchAppCommandHandler) {
        launchAppCommandHandler.onLaunchApp(this);
    }

    public static LaunchAppCommand create() {
        return new LaunchAppCommand();
    }

}
