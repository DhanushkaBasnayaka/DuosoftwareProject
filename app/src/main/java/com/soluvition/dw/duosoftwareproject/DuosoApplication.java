package com.soluvition.dw.duosoftwareproject;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * Created by Dhanushka on 17/06/2017.
 */

public class DuosoApplication extends Application {
    private static DuosoApplication mPhoneMedApplication;
    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        mPhoneMedApplication = this;
        mObjectGraph = ObjectGraph.create(new DuosoModule(this));

    }

    public static DuosoApplication get(Context context) {
        return (DuosoApplication) context.getApplicationContext();
    }

    public final void inject(Object object) {
        mObjectGraph.inject(object);
    }

    public static DuosoApplication getInstance() {
        return mPhoneMedApplication;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}