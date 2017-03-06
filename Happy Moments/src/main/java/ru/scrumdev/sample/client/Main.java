package ru.scrumdev.sample.client;

import com.google.gwt.user.client.ui.*;
import ru.scrumdev.sample.client.ui.View;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

import java.util.ArrayList;
import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private final Messages messages = GWT.create(Messages.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        final View view = new View();
        view.setStyleName("helloWidgetPanel");
        view.updateUI();

        RootPanel.get("panelId").add(view);
    }
}
