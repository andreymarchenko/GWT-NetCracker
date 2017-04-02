package ru.happyMoments.client.events;
import com.google.gwt.event.shared.EventHandler;
import ru.happyMoments.client.events.ChangeDataEvent;

public interface ChangeDataEventHandler extends EventHandler{
    void onChangeData(ChangeDataEvent changeDataEvent);
}
