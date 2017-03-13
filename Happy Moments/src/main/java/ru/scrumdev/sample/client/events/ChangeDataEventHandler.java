package ru.scrumdev.sample.client.events;
import com.google.gwt.event.shared.EventHandler;

public interface ChangeDataEventHandler extends EventHandler{
    void onChangeData(ChangeDataEvent changeDataEvent);
}
