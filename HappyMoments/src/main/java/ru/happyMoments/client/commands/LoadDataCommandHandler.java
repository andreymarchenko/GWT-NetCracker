package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.EventHandler;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;

public interface LoadDataCommandHandler extends EventHandler {
    void onLoadData(LightEventDto lightEventDto, LoadDataCommand loadDataCommand);
}
