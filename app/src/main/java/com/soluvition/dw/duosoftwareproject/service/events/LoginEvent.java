package com.soluvition.dw.duosoftwareproject.service.events;


import com.soluvition.dw.duosoftwareproject.model.User;

/**
 * Created by Dhanushka on 17/06/2017.
 */

public class LoginEvent {

    public User result;

    public LoginEvent(User result) {
        this.result = result;
    }
}
