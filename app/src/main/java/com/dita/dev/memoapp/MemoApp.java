package com.dita.dev.memoapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by bwana.mkaya on 28/01/16.
 */
public class MemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
