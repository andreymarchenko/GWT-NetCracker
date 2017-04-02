package ru.happyMoments.client.entity;


import org.junit.Test;
import ru.happyMoments.client.model.EventDataModel;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.ImageDto;

import java.util.Date;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class EventDataModelTest {
    @Test
    public void testSetNullEvent() {
        EventDataModel eventDataModel = new EventDataModel();
        eventDataModel.setEvent(null);
        assertNull(eventDataModel.getEvent());
    }

/*   @Test
    public void testSetNotNullEvent() {
        EventDataModel eventDataModel = new EventDataModel();
        eventDataModel.setEvent(new EventDto());
        assertEquals(new EventDto(), eventDataModel.getEvent());
    }*/

}