package com.soluvition.dw.duosoftwareproject.service;

import android.app.IntentService;
import android.content.Intent;

import com.soluvition.dw.duosoftwareproject.service.events.DismissWaitingEvent;
import com.soluvition.dw.duosoftwareproject.service.events.FailedEvent;
import com.soluvition.dw.duosoftwareproject.service.events.LoginEvent;
import com.soluvition.dw.duosoftwareproject.service.events.ShowWaitingEvent;
import com.soluvition.dw.duosoftwareproject.service.requestes.LoginRequest;
import com.soluvition.dw.duosoftwareproject.service.responses.LoginResponse;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DuosoftServiceManager extends IntentService {
    public DuosoftServiceManager() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public void login(LoginRequest loginRequest) {
        EventBus.getDefault().post(new ShowWaitingEvent());

        DuosoftService.getInstance().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                EventBus.getDefault().post(new DismissWaitingEvent());

                if (response.isSuccessful()) {
                    if (response.body() != null)
                        EventBus.getDefault().post(new LoginEvent(response.body().result));
                } else {
                    EventBus.getDefault().post(new FailedEvent(response.code()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                EventBus.getDefault().post(new FailedEvent());
                EventBus.getDefault().post(new DismissWaitingEvent());
            }
        });
    }

}
