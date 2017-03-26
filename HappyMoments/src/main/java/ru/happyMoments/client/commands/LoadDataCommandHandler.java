package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.EventHandler;
import ru.happyMoments.client.entity.Event;

public interface LoadDataCommandHandler extends EventHandler {
    void onLoadData(Event event, LoadDataCommand loadDataCommand);
}
