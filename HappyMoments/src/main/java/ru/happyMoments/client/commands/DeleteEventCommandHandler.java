package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.EventHandler;
import ru.happyMoments.shared.dto.EventDto;

public interface DeleteEventCommandHandler extends EventHandler {
    void onDeleteEvent(EventDto EventDto, DeleteEventCommand deleteEventCommand);
}
