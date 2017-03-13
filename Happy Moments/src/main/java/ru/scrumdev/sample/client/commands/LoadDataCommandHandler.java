package ru.scrumdev.sample.client.commands;

import com.google.gwt.event.shared.EventHandler;

public interface LoadDataCommandHandler extends EventHandler {
    void onLoadData(LoadDataCommand loadDataCommand);
}
