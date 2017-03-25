package ru.happyMoments.client.model;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.client.events.ChangeDataEvent;
import ru.happyMoments.client.events.ChangeDataEventHandler;

import javax.inject.Inject;

public class DataModel {

    private EventDto eventDto;
    private EventBus eventBus;

    @Inject
    public void DataModel(EventDto eventDto,
                          EventBus eventBus) {
        this.eventDto = eventDto;
        this.eventBus = eventBus;
    }

    public HandlerRegistration addDataChangedEventHandler(final ChangeDataEventHandler handler) {
        return eventBus.addHandler(ChangeDataEvent.TYPE, handler);
    }

    public void setEventDto(EventDto eventDto) {
        //Проверка на эвент, который уже выбран + тест
        if (eventDto == null) return;
        else {
            this.eventDto = eventDto;
            eventBus.fireEvent(ChangeDataEvent.create());
        }
    }

    public EventDto getEventDto() {
        return eventDto;
    }
}
