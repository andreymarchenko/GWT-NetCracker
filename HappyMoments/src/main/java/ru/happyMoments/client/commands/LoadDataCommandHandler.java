package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.EventHandler;

public interface LoadDataCommandHandler extends EventHandler {
    void onLoadData(LoadDataCommand loadDataCommand);
}
