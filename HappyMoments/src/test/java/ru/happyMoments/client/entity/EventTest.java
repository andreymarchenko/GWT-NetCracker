package ru.happyMoments.client.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void constructor() throws Exception {
        Date date = new Date(21122007);
        ArrayList<Image> list = new ArrayList<Image>(1);
        Event event = new Event(3, "This is first event", new Date(), "First Event", list, "18:00");

        assertEquals(3 , event.getId());
        assertEquals("This is first event" , event.getDescription());
        assertEquals(new Date(21122007) , event.getDate());
        assertEquals("First Event" , event.getName());
        assertEquals(list , event.getImages());
        assertEquals("18:00" , event.getTime());

    }

}