package ru.happyMoments.client.commands;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Image;
import org.vectomatic.file.File;

public class UploadImageCommand extends GwtEvent<UploadImageCommandHandler> {

    File file;

    public static GwtEvent.Type<UploadImageCommandHandler> TYPE = new GwtEvent.Type<UploadImageCommandHandler>();

    public UploadImageCommand(File file) {
        this.file = file;
    }

    @Override
    public Type<UploadImageCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UploadImageCommandHandler handler) {
        handler.onUpdateImage(file, this);
    }

    public static UploadImageCommand create(File file) {
        return new UploadImageCommand(file);
    }
}
