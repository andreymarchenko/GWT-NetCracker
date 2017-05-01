package ru.happyMoments.client.entity;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.junit.Before;
import org.junit.Test;
import ru.happyMoments.client.model.EventListDataModel;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.Factory;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class EventListDataModelTest {

    private static ArrayList<LightEventDto> LIGHT_EVENTS = new ArrayList<LightEventDto>();
    private static final EventBus EVENT_BUS = new SimpleEventBus();

    @Before
    public void addData() {
        LIGHT_EVENTS.add(Factory.createLightEventDto(1.1, 1.1));
    }


    @Test
    public void testSetNotNullEvents() {
        EventListDataModel eventListDataModel = new EventListDataModel(EVENT_BUS);
        eventListDataModel.setLightEvents(LIGHT_EVENTS);
        assertEquals(LIGHT_EVENTS, eventListDataModel.getLightEventDtos());
    }
}
