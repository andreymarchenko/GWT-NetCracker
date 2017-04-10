package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

public class LoadDataCommand extends GwtEvent<LoadDataCommandHandler> {

    LightEventDto lightEventDto;

    public static Type<LoadDataCommandHandler> TYPE = new Type<LoadDataCommandHandler>();

    public LoadDataCommand(LightEventDto lightEventDto) {
        this.lightEventDto = lightEventDto;
    }

    @Override
    public Type<LoadDataCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadDataCommandHandler loadDataCommandHandler) {
        loadDataCommandHandler.onLoadData(lightEventDto, this);
    }

    public static LoadDataCommand create(LightEventDto lightEventDto) {
        return new LoadDataCommand(lightEventDto);
    }
}
