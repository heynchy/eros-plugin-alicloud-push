package com.heyn.erosplugin.wxalicloudpush;

import android.util.Log;

import com.benmu.framework.BMWXApplication;

/**
 * Created by Carry on 2017/8/23.
 */

public class App extends BMWXApplication {
    public static App mInstance;
    public static final String TAG = "Ali_push";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
