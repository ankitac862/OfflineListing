package com.anki.offlinelisting.di.module;


import android.app.Application;
import android.content.Context;

import com.anki.offlinelisting.di.annotation.ApplicationScope;
import com.anki.offlinelisting.di.annotation.contextquilifiers.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ApplicationContextModule {
    @Provides
    @ApplicationScope
    @ApplicationContext
    public static Context provideApplicationContext(Application application){
        return application.getApplicationContext();
    }

}
