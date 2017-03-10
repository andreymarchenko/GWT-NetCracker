package ru.scrumdev.sample.client;

import ru.scrumdev.sample.client.controller.LifeCycle;
import ru.scrumdev.sample.client.modules.Injector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HappyMomentsEntryPoint implements EntryPoint {
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

    public void onModuleLoad() {
        Injector injector = GWT.create(Injector.class);
        LifeCycle lifeCycle = injector.getLifeCycle();
        lifeCycle.start();
    }
}
