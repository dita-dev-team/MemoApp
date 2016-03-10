package com.dita.dev.memoapp.bus;

public class NotificationEvent {
    public String message;
    public Integer length;

    public NotificationEvent() {
        message = null;
        length = null;
    }
}
