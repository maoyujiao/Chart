package com.zqx.zqxcharts;

import android.app.Application;

/**
 * Created by mac on 16/6/27.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
