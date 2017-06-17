package com.soluvition.dw.duosoftwareproject.service.events;

/**
 * Created by Dhanushka on 17/06/2017.
 */

public class FailedEvent {
    public int mErrorCode;

    public FailedEvent() {
    }

    public FailedEvent(int errorCode) {
        mErrorCode = errorCode;
    }
}
