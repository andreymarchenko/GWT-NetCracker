package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import ru.happyMoments.shared.dto.EventDto;

public class DeleteEventCommand extends GwtEvent<DeleteEventCommandHandler> {

    EventDto eventDto;

    public static Type<DeleteEventCommandHandler> TYPE = new Type<DeleteEventCommandHandler>();

    public DeleteEventCommand(EventDto eventDto) {
        this.eventDto = eventDto;
    }

    @Override
    public Type<DeleteEventCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DeleteEventCommandHandler handler) {
        handler.onDeleteEvent(eventDto, this);
    }

    public static DeleteEventCommand create(EventDto eventDto) {
        return new DeleteEventCommand(eventDto);
    }
}
