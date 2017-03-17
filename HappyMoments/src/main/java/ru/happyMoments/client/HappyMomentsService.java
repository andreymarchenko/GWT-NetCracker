package ru.happyMoments.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("HappyMomentsService")
public interface HappyMomentsService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use HappyMomentsService.App.getInstance() to access static instance of HappyMomentsServiceAsync
     */
    public static class App {
        private static HappyMomentsServiceAsync ourInstance = GWT.create(HappyMomentsService.class);

        public static synchronized HappyMomentsServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
