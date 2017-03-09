package ru.scrumdev.sample.client.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import ru.scrumdev.sample.client.Controller;
import ru.scrumdev.sample.client.Presenter;
import ru.scrumdev.sample.client.ui.View;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(View.class).in(Singleton.class);
        bind(Presenter.class).in(Singleton.class);
        bind(Controller.class).in(Singleton.class);
    }
}
