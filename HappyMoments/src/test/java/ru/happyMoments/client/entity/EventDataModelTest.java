package ru.happyMoments.client.entity;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.junit.Test;
import ru.happyMoments.client.model.EventDataModel;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class EventDataModelTest {

    private static final EventDto FIRST_EVENT = new EventDto(1, "", "", "", new ImageDto(), "", 1.0, 1.0);
    private static final EventBus EVENT_BUS = new SimpleEventBus();

    @Test
    public void testSetNullEvent() {
        EventDataModel eventDataModel = new EventDataModel();
        eventDataModel.setEvent(null);
        assertNull(eventDataModel.getEvent());
    }

    @Test
    public void testSetNotNullEvent() {
        EventDataModel eventDataModel = new EventDataModel();
        eventDataModel.setEvent(FIRST_EVENT);
        eventDataModel.setEventBus(EVENT_BUS);
        assertEquals(FIRST_EVENT, eventDataModel.getEvent());
    }

}