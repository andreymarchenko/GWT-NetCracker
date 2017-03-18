package ru.happyMoments.client.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {
    @Test
    public void getTime() throws Exception {
        Event event = new Event();
        String time = event.getTime();
        assertEquals("21.00", time);
    }

    @Test
    public void getId() throws Exception {
        Event event = new Event();
        int id = event.getId();
        assertEquals(1, id);
    }

    @Test
    public void getDescription() throws Exception {
        Event event = new Event();
        String desc = event.getDescription();
        assertEquals("Some description", desc);
    }

    @Test
    public void getName() throws Exception {
        Event event = new Event();
        String name = event.getName();
        assertEquals("First Event", name);
    }

}