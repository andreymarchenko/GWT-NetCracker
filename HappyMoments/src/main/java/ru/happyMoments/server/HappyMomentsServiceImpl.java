package ru.happyMoments.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.happyMoments.client.HappyMomentsService;

public class HappyMomentsServiceImpl extends RemoteServiceServlet implements HappyMomentsService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}