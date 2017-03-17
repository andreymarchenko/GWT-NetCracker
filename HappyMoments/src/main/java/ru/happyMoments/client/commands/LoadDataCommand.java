package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.GwtEvent;

public class LoadDataCommand extends GwtEvent<LoadDataCommandHandler> {

    public static Type<LoadDataCommandHandler> TYPE = new Type<LoadDataCommandHandler>();

    @Override
    public Type<LoadDataCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadDataCommandHandler loadDataCommandHandler) {
        loadDataCommandHandler.onLoadData(this);
    }

    public static LoadDataCommand create() {
        return new LoadDataCommand();
    }
}
