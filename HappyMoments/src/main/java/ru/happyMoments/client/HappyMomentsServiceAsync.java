package ru.happyMoments.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HappyMomentsServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
