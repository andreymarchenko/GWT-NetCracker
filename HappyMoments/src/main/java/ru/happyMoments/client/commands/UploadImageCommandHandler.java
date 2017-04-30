package ru.happyMoments.client.commands;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.ui.Image;
import org.vectomatic.file.File;

public interface UploadImageCommandHandler extends EventHandler {
    void onUpdateImage(File file, UploadImageCommand uploadImageCommand);
}
