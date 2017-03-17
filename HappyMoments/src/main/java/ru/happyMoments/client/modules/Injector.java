package ru.happyMoments.client.modules;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import ru.happyMoments.client.controller.LifeCycle;


@GinModules(Module.class)
public interface Injector extends Ginjector {
    LifeCycle getLifeCycle();
}
