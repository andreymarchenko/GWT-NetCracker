package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.EventHandler;
import ru.happyMoments.shared.dto.EventDto;

public interface EditEventCommandHandler extends EventHandler {
    void onEditEvent(EventDto EventDto, EditEventCommand editEventCommand);
}
