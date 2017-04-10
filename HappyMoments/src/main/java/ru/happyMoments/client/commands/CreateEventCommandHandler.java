package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.EventHandler;
import ru.happyMoments.shared.dto.EventDto;

public interface CreateEventCommandHandler extends EventHandler {
    void onCreateEvent(EventDto EventDto, CreateEventCommand createEventCommand);
}
