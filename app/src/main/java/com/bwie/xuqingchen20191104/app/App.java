package com.bwie.xuqingchen20191104.app;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);
    }
}
