package com.soluvition.dw.duosoftwareproject;

import com.soluvition.dw.duosoftwareproject.service.DuosoftServiceManager;
import com.soluvition.dw.duosoftwareproject.activityview.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dhanushka on 17/06/2017.
 */


@Module(injects = {

        MainActivity.class,


}, complete = true)
public class DuosoModule {
    private DuosoApplication mduosoAppApplication;

    public DuosoModule(DuosoApplication duosoAppApplication) {
        this.mduosoAppApplication = duosoAppApplication;
    }

    @Provides
    public DuosoftServiceManager provideDuosoftServiceManager() {
        return new DuosoftServiceManager();
    }

}
