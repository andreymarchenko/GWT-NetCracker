package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

public class CreateEventCommand extends GwtEvent<CreateEventCommandHandler> {

    EventDto eventDto;

    public static Type<CreateEventCommandHandler> TYPE = new Type<CreateEventCommandHandler>();

    public CreateEventCommand(EventDto eventDto) {
        this.eventDto = eventDto;
    }

    @Override
    public Type<CreateEventCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CreateEventCommandHandler handler) {
        handler.onCreateEvent(eventDto, this);
    }

    public static CreateEventCommand create(EventDto eventDto) {
        return new CreateEventCommand(eventDto);
    }
}
