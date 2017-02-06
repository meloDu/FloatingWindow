package com.rmtd.melo.floatingwindow;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by melo on 2017/2/6.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
