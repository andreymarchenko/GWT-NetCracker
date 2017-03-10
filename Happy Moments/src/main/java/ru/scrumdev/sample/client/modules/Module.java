package ru.scrumdev.sample.client.modules;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import ru.scrumdev.sample.client.controller.Controller;
import ru.scrumdev.sample.client.controller.LifeCycle;
import ru.scrumdev.sample.client.model.DataModel;
import ru.scrumdev.sample.client.presenter.Presenter;
import ru.scrumdev.sample.client.view.View;

public class Module extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(Presenter.class).in(Singleton.class);
        bind(Controller.class).in(Singleton.class);
        bind(DataModel.class).in(Singleton.class);
        bind(View.class).in(Singleton.class);
        bind(LifeCycle.class).in(Singleton.class);
    }
}
