package ru.happyMoments.client.entryPoint;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.Defaults;
import ru.happyMoments.client.controller.LifeCycle;
import ru.happyMoments.client.modules.Injector;

public class HappyMoments implements EntryPoint {

    @Override
    public void onModuleLoad() {
        Defaults.setServiceRoot(GWT.getHostPageBaseURL());
        Injector injector = GWT.create(Injector.class);
        LifeCycle lifeCycle = injector.getLifeCycle();
        lifeCycle.start();
    }
}

