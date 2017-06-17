package com.soluvition.dw.duosoftwareproject.service;

import android.app.IntentService;
import android.content.Intent;

import com.soluvition.dw.duosoftwareproject.service.events.DismissWaitingEvent;
import com.soluvition.dw.duosoftwareproject.service.events.FailedEvent;
import com.soluvition.dw.duosoftwareproject.service.events.LoginEvent;
import com.soluvition.dw.duosoftwareproject.service.events.LoginUserEvent;
import com.soluvition.dw.duosoftwareproject.service.events.MyTicketsListEvent;
import com.soluvition.dw.duosoftwareproject.service.events.ShowWaitingEvent;
import com.soluvition.dw.duosoftwareproject.service.requestes.DetailsRequest;
import com.soluvition.dw.duosoftwareproject.service.requestes.LoginRequest;
import com.soluvition.dw.duosoftwareproject.service.requestes.MyTicketsListRequest;
import com.soluvition.dw.duosoftwareproject.service.responses.LoginResponse;
import com.soluvition.dw.duosoftwareproject.service.responses.MyTicketsListResponses;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.ResponseBody;
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
                        EventBus.getDefault().post(new LoginUserEvent(response.body()));
                } else {
                    EventBus.getDefault().post(new FailedEvent(response.body().message));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                EventBus.getDefault().post(new DismissWaitingEvent());
                EventBus.getDefault().post(new FailedEvent());

            }
        });
    }

    public void getMyTicketsList(MyTicketsListRequest mMyTicketsListRequest) {

        EventBus.getDefault().postSticky(new ShowWaitingEvent());

        DuosoftService.getLiteticket().getMyTicketsList(mMyTicketsListRequest).enqueue(new Callback<MyTicketsListResponses>() {
            @Override
            public void onResponse(Call<MyTicketsListResponses> call, Response<MyTicketsListResponses> response) {
                EventBus.getDefault().post(new DismissWaitingEvent());
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        EventBus.getDefault().post(new MyTicketsListEvent(response.body()));
                } else {
                    EventBus.getDefault().post(new FailedEvent(response.body().IsSuccess));
                }


            }

            @Override
            public void onFailure(Call<MyTicketsListResponses> call, Throwable t) {
                EventBus.getDefault().post(new FailedEvent());
                EventBus.getDefault().post(new DismissWaitingEvent());
            }
        });
    }

    public void getDetails(DetailsRequest mDetailsRequest) {

        EventBus.getDefault().postSticky(new ShowWaitingEvent());

        DuosoftService.getLiteticket().getDetails(mDetailsRequest).enqueue(new Callback<MyTicketsListResponses>() {
            @Override
            public void onResponse(Call<MyTicketsListResponses> call, Response<MyTicketsListResponses> response) {
                EventBus.getDefault().post(new DismissWaitingEvent());
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        EventBus.getDefault().post(new MyTicketsListEvent(response.body()));
                } else {
                    EventBus.getDefault().post(new FailedEvent(response.body().IsSuccess));
                }

            }

            @Override
            public void onFailure(Call<MyTicketsListResponses> call, Throwable t) {
                EventBus.getDefault().post(new FailedEvent());
                EventBus.getDefault().post(new DismissWaitingEvent());
            }
        });
    }


}
