package ru.scrumdev.sample.client.controller;

import ru.scrumdev.sample.client.view.View;

import javax.inject.Inject;

public class LifeCycle {
    private View view;

    @Inject
    public LifeCycle(View view) {
        this.view = view;
    }

    public void start() {
        view.createUI();
    }
}
