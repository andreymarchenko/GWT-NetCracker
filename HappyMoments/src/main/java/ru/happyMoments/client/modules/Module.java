package ru.happyMoments.client.modules;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import ru.happyMoments.client.controller.Controller;
import ru.happyMoments.client.controller.LifeCycle;
import ru.happyMoments.client.model.DataModel;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.client.view.BasicMapWidget;
import ru.happyMoments.client.view.View;

public class Module extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(Presenter.class).asEagerSingleton();
        bind(Controller.class).in(Singleton.class);
        bind(DataModel.class).in(Singleton.class);
        bind(View.class).in(Singleton.class);
        bind(LifeCycle.class).in(Singleton.class);
    }
}
