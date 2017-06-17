package com.soluvition.dw.duosoftwareproject.service;


import com.soluvition.dw.duosoftwareproject.DuosoConstant;
import com.soluvition.dw.duosoftwareproject.service.requestes.LoginRequest;
import com.soluvition.dw.duosoftwareproject.service.responses.LoginResponse;
import com.soluvition.dw.duosoftwareproject.service.responses.MyTicketsListResponses;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * Created by Dhanushka on 17/06/2017.
 */

public interface DuosoftApi {

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest userLoginRequest);

    @Streaming
    @GET("DVP/API/1.0.0.0/MyTickets/{limit}/{pageNumber}")
    Call<MyTicketsListResponses> getMyTicketsList(
            @Header(DuosoConstant.TOKEN) String Authorization,
            @Path(DuosoConstant.LIMIT) String limit,
            @Path(DuosoConstant.PAGENUMBER) String pageNumber);

    @Streaming
    @GET("DVP/API/1.0.0.0/MyTickets/{_id}")
    Call<MyTicketsListResponses> getDetails(
            @Header(DuosoConstant.TOKEN) String token,
            @Path(DuosoConstant.ID) String _id);

}
