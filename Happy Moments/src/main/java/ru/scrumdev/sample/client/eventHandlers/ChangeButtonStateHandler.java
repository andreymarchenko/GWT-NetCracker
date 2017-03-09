package ru.scrumdev.sample.client.eventHandlers;
import com.google.gwt.event.shared.EventHandler;
import ru.scrumdev.sample.client.events.ChangeButtonEvent;

public interface ChangeButtonStateHandler extends EventHandler{
    void onChangeButtonState(ChangeButtonEvent event);
}
