package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import ru.happyMoments.shared.dto.EventDto;

public class EditEventCommand extends GwtEvent<EditEventCommandHandler> {

    EventDto eventDto;

    public static Type<EditEventCommandHandler> TYPE = new Type<EditEventCommandHandler>();

    public EditEventCommand(EventDto eventDto) {
        this.eventDto = eventDto;
    }

    @Override
    public Type<EditEventCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditEventCommandHandler handler) {
        handler.onEditEvent(eventDto, this);
    }

    public static EditEventCommand create(EventDto eventDto) {
        return new EditEventCommand(eventDto);
    }
}
