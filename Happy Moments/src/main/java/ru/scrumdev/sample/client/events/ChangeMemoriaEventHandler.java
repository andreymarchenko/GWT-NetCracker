package ru.scrumdev.sample.client.events;
import com.google.gwt.event.shared.EventHandler;

public interface ChangeMemoriaEventHandler extends EventHandler{
    void onChangeMemoria(ChangeMemoriaEvent changeMemoriaEvent);
}
