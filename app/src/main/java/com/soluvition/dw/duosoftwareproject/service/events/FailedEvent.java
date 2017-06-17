package com.soluvition.dw.duosoftwareproject.service.events;

/**
 * Created by Dhanushka on 17/06/2017.
 */

public class FailedEvent {
    public String message;

    public FailedEvent() {
    }

    public FailedEvent(String errorCode) {
        message = errorCode;
    }
}
