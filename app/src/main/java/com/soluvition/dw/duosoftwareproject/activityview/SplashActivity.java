package com.soluvition.dw.duosoftwareproject.activityview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.soluvition.dw.duosoftwareproject.R;
import com.soluvition.dw.duosoftwareproject.helper.DuosoftHelper;
import com.soluvition.dw.duosoftwareproject.service.DuosoftServiceManager;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {
    @Inject
    public DuosoftServiceManager mDuosoftServiceManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        if (DuosoftHelper.getUserLoggedInState(SplashActivity.this)) {

            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();

        } else {
            startActivity(new Intent(SplashActivity.this, LogingActivity.class));
            finish();
        }
    }
}
