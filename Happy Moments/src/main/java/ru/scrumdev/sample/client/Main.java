package ru.scrumdev.sample.client;

import com.google.gwt.user.client.ui.*;
import com.google.inject.Injector;
import ru.scrumdev.sample.client.ui.View;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

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

        //View view = new View();
        Injector injector = GWT.create(Injector.class);
        View view = injector.getInstance(View.class);
        view.setStyleName("helloWidgetPanel");
        view.updateUI();

        RootPanel.get("panelId").add(view);
    }
}
