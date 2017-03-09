package ru.scrumdev.sample.client.modules;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import ru.scrumdev.sample.client.Controller;
import ru.scrumdev.sample.client.DataModel;
import ru.scrumdev.sample.client.Presenter;
import ru.scrumdev.sample.client.ui.View;


public class Module extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(Presenter.class).in(Singleton.class);
        bind(Controller.class).in(Singleton.class);
        bind(DataModel.class).in(Singleton.class);
        bind(View.class).in(Singleton.class);
    }
}
