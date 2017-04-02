package ru.happyMoments.client.entity;

import org.junit.Test;
import ru.happyMoments.client.model.EventDataModel;
import ru.happyMoments.client.model.EventListDataModel;

import static junit.framework.TestCase.assertNull;

public class EventListDataModelTest {
    @Test
    public void testSetNullEvent() {
        EventListDataModel eventListDataModel = new EventListDataModel();
        eventListDataModel.setLightEvents(null);
        assertNull(eventListDataModel.getLightEventDtos());
    }
}
