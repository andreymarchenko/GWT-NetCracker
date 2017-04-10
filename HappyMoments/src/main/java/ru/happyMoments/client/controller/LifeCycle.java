package ru.happyMoments.client.controller;

import com.google.web.bindery.event.shared.EventBus;
import ru.happyMoments.client.commands.LoadLightEventsCommand;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.client.view.View;

import javax.inject.Inject;

public class LifeCycle {
    private View view;
    private Presenter presenter;
    private EventBus eventBus;

    @Inject
    public LifeCycle(View view,
                     Presenter presenter,
                     EventBus eventBus) {
        this.view = view;
        this.eventBus = eventBus;
        this.presenter = presenter;
    }

    public void start() {
        view.createUI();
        presenter.bind();
        eventBus.fireEvent(LoadLightEventsCommand.create());
    }
}
