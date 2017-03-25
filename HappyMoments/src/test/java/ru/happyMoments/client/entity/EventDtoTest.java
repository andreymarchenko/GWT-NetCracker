package ru.happyMoments.client.entity;

import org.junit.Test;
import ru.happyMoments.shared.dto.EventDto;

import static org.junit.Assert.*;

public class EventDtoTest {
    @Test
    public void getTime() throws Exception {
        EventDto eventDto = new EventDto();
        String time = eventDto.getTime();
        assertEquals("21.00", time);
    }

    @Test
    public void getId() throws Exception {
        EventDto eventDto = new EventDto();
        int id = eventDto.getId();
        assertEquals(1, id);
    }

    @Test
    public void getDescription() throws Exception {
        EventDto eventDto = new EventDto();
        String desc = eventDto.getDescription();
        assertEquals("Some description", desc);
    }

    @Test
    public void getName() throws Exception {
        EventDto eventDto = new EventDto();
        String name = eventDto.getName();
        assertEquals("First EventDto", name);
    }

}