package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.EventHandler;

public interface LaunchAppCommandHandler extends EventHandler {
    void onLaunchApp(LaunchAppCommand launchAppCommand);
}
