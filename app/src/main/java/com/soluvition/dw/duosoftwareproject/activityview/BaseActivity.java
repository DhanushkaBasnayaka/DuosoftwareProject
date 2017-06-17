package com.soluvition.dw.duosoftwareproject.activityview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import org.greenrobot.eventbus.Subscribe;

import com.soluvition.dw.duosoftwareproject.DuosoApplication;
import com.soluvition.dw.duosoftwareproject.DuosoConstant;
import com.soluvition.dw.duosoftwareproject.R;
import com.soluvition.dw.duosoftwareproject.helper.DuosoftHelper;
import com.soluvition.dw.duosoftwareproject.service.DuosoftServiceManager;
import com.soluvition.dw.duosoftwareproject.service.events.DismissWaitingEvent;
import com.soluvition.dw.duosoftwareproject.service.events.ShowWaitingEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Dhanushka on 22/05/2017.
 */

public class BaseActivity extends AppCompatActivity {

    public Dialog mProgress;
    protected String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DuosoApplication.get(this).inject(this);
        EventBus.getDefault().register(this);
        accessToken = DuosoftHelper.getAccessToken(this);

    }

    public void showWaiting(Context context) {

        if (mProgress == null) {
            mProgress = new Dialog(context, R.style.Progressbar);
            mProgress.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgress.setContentView(R.layout.custom_progress_spinner);
            mProgress.setCancelable(false);
        }

        if (!mProgress.isShowing()) {
            mProgress.show();
        }
    }

    public <E extends View> E findViewExtended(int resourceId) {
        return (E) super.findViewById(resourceId);
    }

    public void dismissWaiting() {

        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
            mProgress = null;
        }
    }

    @Subscribe
    public void onEvent(ShowWaitingEvent event) {
        showWaiting(this);
    }

    @Subscribe
    public void onEvent(DismissWaitingEvent event) {
        dismissWaiting();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    public void onBackPressed() {
        super.onBackPressed();
    }

}

