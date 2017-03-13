package ru.scrumdev.sample.client.controller;

import ru.scrumdev.sample.client.presenter.Presenter;
import ru.scrumdev.sample.client.view.View;

import javax.inject.Inject;

public class LifeCycle {
    private View view;
    private Presenter presenter;

    @Inject
    public LifeCycle(View view,
                     Presenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    public void start() {
        view.createUI();
        presenter.bind();
        view.bind();
    }
}
