package com.soluvition.dw.duosoftwareproject.service.events;

import com.soluvition.dw.duosoftwareproject.service.responses.LoginResponse;

/**
 * Created by Macbook on 6/18/17.
 */

public class LoginUserEvent {
    public LoginResponse result;

    public LoginUserEvent(LoginResponse body) {
        this.result = body;
    }
}
