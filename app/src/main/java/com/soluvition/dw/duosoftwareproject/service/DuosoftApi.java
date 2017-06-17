package com.soluvition.dw.duosoftwareproject.service;


import com.soluvition.dw.duosoftwareproject.service.requestes.LoginRequest;
import com.soluvition.dw.duosoftwareproject.service.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dhanushka on 17/06/2017.
 */

public interface DuosoftApi {

    @POST("api/v1/userLogin")
    Call<LoginResponse> login(@Body LoginRequest userLoginRequest);


}
