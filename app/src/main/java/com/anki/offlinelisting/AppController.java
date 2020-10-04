package com.anki.offlinelisting;

import android.content.Context;

import com.anki.offlinelisting.di.compontent.ApplicationControllerCompontent;
import com.anki.offlinelisting.di.compontent.DaggerApplicationControllerCompontent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AppController extends DaggerApplication {
    ApplicationControllerCompontent appCompontent;
    private static AppController mInstance;
    private static Context applicationContext;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return appCompontent;
    }
    @Override
    public void onCreate() {
        initAndroidInjection();
        super.onCreate();
        mInstance = this;
    }

    private void initAndroidInjection() {
        appCompontent = DaggerApplicationControllerCompontent.factory().create(this);
        appCompontent.inject(this);
    }

    public ApplicationControllerCompontent getAppCompontent() {
        return appCompontent;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return applicationContext;
    }

}
