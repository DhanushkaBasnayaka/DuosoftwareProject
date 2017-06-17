package com.soluvition.dw.duosoftwareproject.service.events;


import com.soluvition.dw.duosoftwareproject.model.User;
import com.soluvition.dw.duosoftwareproject.service.responses.LoginResponse;

/**
 * Created by Dhanushka on 17/06/2017.
 */

public class LoginEvent {

    public  LoginResponse result;

    public LoginEvent(LoginResponse body) {
        this.result = body;
    }
}
