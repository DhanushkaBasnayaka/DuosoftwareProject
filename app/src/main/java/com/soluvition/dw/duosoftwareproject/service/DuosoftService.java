package com.soluvition.dw.duosoftwareproject.service;

import com.soluvition.dw.duosoftwareproject.DuosoConstant;
import com.soluvition.dw.duosoftwareproject.service.requestes.LoginRequest;
import com.soluvition.dw.duosoftwareproject.service.responses.LoginResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dhanushka on 17/06/2017.
 */

public class DuosoftService {

    private static DuosoftService mPhoneMedService;
    private static final int TIME_OUT_DURATION = 300;
    private DuosoftApi mPhoneMedApi;

    /**
     * Returns the instance of this singleton.
     */
    public static DuosoftService getInstance() {
        if (mPhoneMedService == null) {
            mPhoneMedService = new DuosoftService();
        }
        return mPhoneMedService;
    }
    /**
     * Private singleton constructor.
     */

    private DuosoftService() {

        // Add the interceptor to OkHttpClient
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // TO DO in the production level change to LEVEL.NONE

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);

        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DuosoConstant.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.mPhoneMedApi = retrofit.create(DuosoftApi.class);
    }

    public Call<LoginResponse> login(LoginRequest loginRequest) {
        return this.mPhoneMedApi.login(loginRequest);
    }


}
