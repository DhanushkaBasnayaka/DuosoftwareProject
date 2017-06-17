package com.soluvition.dw.duosoftwareproject.service.events;

import com.soluvition.dw.duosoftwareproject.service.responses.MyTicketsListResponses;

/**
 * Created by Macbook on 6/17/17.
 */

public class MyTicketsListEvent {

    public MyTicketsListResponses  result;
    public MyTicketsListEvent(MyTicketsListResponses body) {
        this.result = body;
    }
}
